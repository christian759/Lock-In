package com.grind.lockin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grind.lockin.data.model.AppUsage
import kotlinx.coroutines.flow.Flow

@Dao
interface AppUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsage(appUsage: AppUsage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsages(usages: List<AppUsage>)

    @Query("SELECT * FROM app_usage WHERE date = :today ORDER BY usageTime DESC")
    fun getAllUsageToday(today: Long = getTodayEpoch()): Flow<List<AppUsage>>

    @Query("SELECT * FROM app_usage WHERE packageName = :pkg ORDER BY date DESC")
    fun getUsageForApp(pkg: String): Flow<List<AppUsage>>

    @Query("DELETE FROM app_usage")
    suspend fun clearAll()

    // Optional: total time spent today
    @Query("SELECT SUM(usageTime) FROM app_usage WHERE date = :today")
    fun getTotalUsageToday(today: Long = getTodayEpoch()): Flow<Long>

    companion object {
        fun getTodayEpoch(): Long {
            return System.currentTimeMillis() / (1000 * 60 * 60 * 24) // naive epoch day
        }
    }
}
