package com.example.praktichna_robota_2_var_19.domain.usecase

import com.example.praktichna_robota_2_var_19.domain.model.Job
import com.example.praktichna_robota_2_var_19.domain.repository.JobRepository

class GetJobsUseCase(
    private val repository: JobRepository
) {

    suspend operator fun invoke(): List<Job> {
        return repository.getJobs()
    }
}