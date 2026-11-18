package com.example.praktichna_robota_2_var_19.domain.repository

import com.example.praktichna_robota_2_var_19.domain.model.Job

interface JobRepository {

    suspend fun getJobs(): List<Job>
}