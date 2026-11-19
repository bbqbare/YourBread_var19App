package com.example.praktichna_robota_2_var_19.di

import com.example.praktichna_robota_2_var_19.data.repository.remote.JobApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module

val networkModule = module {

    single<Retrofit> { _ ->

        Retrofit.Builder()
            .baseUrl("https://remotive.com/api/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .build()
    }

    single<JobApiService> { _ ->

        this.get<Retrofit>().create(JobApiService::class.java)
    }
}