package com.example.praktichna_robota_2_var_19.domain.usecase

import com.example.praktichna_robota_2_var_19.domain.model.Job
import com.example.praktichna_robota_2_var_19.domain.model.Result
import com.example.praktichna_robota_2_var_19.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow

class GetJobsUseCase(
    private val repository: JobRepository
) {

    fun observeJobs(): Flow<List<Job>> {

        return repository.observeJobs()
    }

    suspend fun refresh(): Result<Unit> {

        return repository.refresh()
    }
}