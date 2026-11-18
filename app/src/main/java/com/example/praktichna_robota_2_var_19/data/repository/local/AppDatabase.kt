package com.example.praktichna_robota_2_var_19.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [JobEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(JobConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun jobDao(): JobDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                ).build().also { database ->

                    INSTANCE = database
                }
            }
        }
    }
}