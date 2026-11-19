package com.example.praktichna_robota_2_var_19.domain.repository

import com.example.praktichna_robota_2_var_19.domain.model.Job
import com.example.praktichna_robota_2_var_19.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface JobRepository {

    fun observeJobs(): Flow<List<Job>>

    suspend fun refresh(): Result<Unit>
}