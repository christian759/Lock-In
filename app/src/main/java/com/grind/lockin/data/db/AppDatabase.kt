package com.grind.lockin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grind.lockin.data.dao.*
import com.grind.lockin.data.model.*

@Database(
    entities = [BlockedApp::class, FocusSession::class, AppUsage::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blockedAppDao(): BlockedAppDao
    abstract fun focusSessionDao(): FocusSessionDao
    abstract fun appUsageDao(): AppUsageDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                                context,
                                AppDatabase::class.java,
                                "lockin_db"
                            ).fallbackToDestructiveMigration(false).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}