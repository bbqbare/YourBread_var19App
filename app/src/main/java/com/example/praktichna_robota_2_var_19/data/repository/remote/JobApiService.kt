package com.example.praktichna_robota_2_var_19.data.repository.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface JobApiService {

    @GET("remote-jobs")
    suspend fun getJobs(
        @Query("category")
        category: String = "software-dev",

        @Query("limit")
        limit: Int = 20,

        @Query("search")
        search: String? = null,

        @Query("company_name")
        companyName: String? = null
    ): RemotiveResponseDto
}