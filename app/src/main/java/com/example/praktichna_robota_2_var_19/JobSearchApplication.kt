package com.example.praktichna_robota_2_var_19

import android.app.Application
import com.example.praktichna_robota_2_var_19.di.dbModule
import com.example.praktichna_robota_2_var_19.di.networkModule
import com.example.praktichna_robota_2_var_19.di.repositoryModule
import com.example.praktichna_robota_2_var_19.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JobSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()

            androidContext(this@JobSearchApplication)

            modules(
                networkModule,
                dbModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}