package com.grind.lockin.service

import android.app.usage.UsageStatsManager
import android.content.Context
import com.grind.lockin.data.dao.AppUsageDao
import com.grind.lockin.data.model.AppUsage
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class ForegroundAppTracker(
    private val context: Context,
    private val dao: AppUsageDao
) {
    private var lastApp: String? = null
    private var lastAppStartTime = 0L

    fun startTracking() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(1000)

                val usageStatsManager =
                    context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
                val endTime = System.currentTimeMillis()
                val startTime = endTime - 1000 * 10

                val stats = usageStatsManager
                    .queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime)
                    .maxByOrNull { it.lastTimeUsed }

                val currentApp = stats?.packageName
                if (currentApp != null && currentApp != lastApp) {
                    val now = System.currentTimeMillis()

                    lastApp?.let {
                        val duration = now - lastAppStartTime
                        if (duration > 1000) {
                            val usage = AppUsage(
                                packageName = it,
                                usageTime = duration,
                                date = getTodayEpoch()
                            )
                            withContext(Dispatchers.IO) {
                                dao.insertUsage(usage)
                            }
                        }
                    }

                    lastApp = currentApp
                    lastAppStartTime = now
                }
            }
        }
    }

    private fun getTodayEpoch(): Long {
        val millisInDay = TimeUnit.DAYS.toMillis(1)
        return System.currentTimeMillis() / millisInDay
    }
}
