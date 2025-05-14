package com.grind.lockin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grind.lockin.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppUsageDao {
    @Insert
    suspend fun insert(usage: AppUsage)

    @Query("SELECT * FROM app_usage WHERE packageName = :pkg ORDER BY startTime DESC")
    fun getUsageByPackage(pkg: String): Flow<List<AppUsage>>

    @Query("SELECT SUM(duration) FROM app_usage WHERE date(startTime / 1000, 'unixepoch') = date('now')")
    fun getTodayUsage(): Flow<Long>
}