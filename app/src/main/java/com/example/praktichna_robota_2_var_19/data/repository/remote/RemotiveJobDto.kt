package com.example.praktichna_robota_2_var_19.data.repository.remote

import com.google.gson.annotations.SerializedName

data class RemotiveResponseDto(

    @SerializedName("jobs")
    val jobs: List<RemotiveJobDto>? = null
)

data class RemotiveJobDto(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("company_name")
    val companyName: String? = null,

    @SerializedName("candidate_required_location")
    val location: String? = null,

    @SerializedName("salary")
    val salary: String? = null,

    @SerializedName("tags")
    val tags: List<String>? = null,

    @SerializedName("job_type")
    val jobType: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("url")
    val url: String? = null
)