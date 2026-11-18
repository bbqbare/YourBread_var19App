package com.example.praktichna_robota_2_var_19.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface JobApiService {

    @GET("remote-jobs")
    suspend fun getJobs(
        @Query("category") category: String = "software-dev",
        @Query("limit") limit: Int = 20
    ): RemotiveResponseDto
}