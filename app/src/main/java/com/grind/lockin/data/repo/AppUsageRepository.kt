package com.grind.lockin.data.repo

import com.grind.lockin.data.dao.AppUsageDao
import com.grind.lockin.data.db.AppDatabase
import com.grind.lockin.data.model.AppUsage
import com.grind.lockin.utils.AppGlobals
import kotlinx.coroutines.flow.Flow

class AppUsageRepository private constructor(
    private val appUsageDao: AppUsageDao
) {

    companion object {
        @Volatile
        private var instance: AppUsageRepository? = null

        fun getInstance(): AppUsageRepository {
            return instance ?: synchronized(this) {
                instance ?: AppUsageRepository(
                    AppDatabase.getDatabase(AppGlobals.getContext()).appUsageDao()
                ).also { instance = it }
            }
        }
    }

    fun getTodayUsage(): Flow<Long> {
        return appUsageDao.getTotalUsageToday()
    }

    fun getUsageByPackage(pkg: String): Flow<List<AppUsage>> {
        return appUsageDao.getUsageForApp(pkg)
    }

    fun getAllUsageToday(): Flow<List<AppUsage>> {
        return appUsageDao.getAllUsageToday()
    }

    suspend fun insertUsage(appUsage: AppUsage) {
        appUsageDao.insertUsage(appUsage)
    }

    suspend fun insertUsages(usages: List<AppUsage>) {
        appUsageDao.insertUsages(usages)
    }

    suspend fun clearAllUsage() {
        appUsageDao.clearAll()
    }
}
