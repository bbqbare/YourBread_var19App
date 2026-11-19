package com.example.praktichna_robota_2_var_19.di

import com.example.praktichna_robota_2_var_19.data.repository.JobRepositoryImpl
import com.example.praktichna_robota_2_var_19.data.repository.local.AppDatabase
import com.example.praktichna_robota_2_var_19.data.repository.local.JobDao
import com.example.praktichna_robota_2_var_19.data.repository.remote.JobApiService
import com.example.praktichna_robota_2_var_19.domain.repository.JobRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<JobRepository> { _ ->

        JobRepositoryImpl(
            api = this.get<JobApiService>(),
            database = this.get<AppDatabase>(),
            jobDao = this.get<JobDao>()
        )
    }
}