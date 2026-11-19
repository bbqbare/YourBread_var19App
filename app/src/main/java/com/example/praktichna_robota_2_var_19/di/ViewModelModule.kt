package com.example.praktichna_robota_2_var_19.di

import com.example.praktichna_robota_2_var_19.ListViewModel
import com.example.praktichna_robota_2_var_19.domain.usecase.GetJobsUseCase
import com.example.praktichna_robota_2_var_19.domain.repository.JobRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory<GetJobsUseCase> { _ ->

        GetJobsUseCase(
            this.get<JobRepository>()
        )
    }

    viewModel { _ ->

        ListViewModel(
            this.get<GetJobsUseCase>()
        )
    }
}