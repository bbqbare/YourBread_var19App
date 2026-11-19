package com.example.praktichna_robota_2_var_19.data.repository.local

import androidx.room.Database
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
}