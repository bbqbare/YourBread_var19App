package com.example.praktichna_robota_2_var_19.di

import androidx.room.Room
import com.example.praktichna_robota_2_var_19.data.repository.local.AppDatabase
import com.example.praktichna_robota_2_var_19.data.repository.local.JobDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single<AppDatabase> { _ ->

        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    single<JobDao> { _ ->

        this.get<AppDatabase>().jobDao()
    }
}