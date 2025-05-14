package com.grind.lockin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_usage")
data class AppUsage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val packageName: String,
    val usageTime: Long, // in milliseconds
    val date: Long       // epoch day
)
