package com.grind.lockin.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.grind.lockin.data.model.BlockedApp
import kotlinx.coroutines.flow.Flow

@Dao
interface BlockedAppDao {
    @Query("SELECT * FROM blocked_apps")
    fun getAll(): Flow<List<BlockedApp>>

    @Insert
    suspend fun insert(app: BlockedApp)

    @Delete
    suspend fun delete(app: BlockedApp)
}