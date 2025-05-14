package com.grind.lockin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.grind.lockin.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FocusSessionDao {
    @Insert
    suspend fun insert(session: FocusSession)

    @Query("SELECT * FROM focus_sessions ORDER BY startTime DESC")
    fun getAll(): Flow<List<FocusSession>>
}