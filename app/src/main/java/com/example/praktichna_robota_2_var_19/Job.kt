package com.example.praktichna_robota_2_var_19

enum class JobStatus {
    OPEN,
    URGENT,
    CLOSED
}

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

data class Job(
    val id: Int,
    val title: String,
    val company: String,
    val location: String,
    val salary: Double,
    val tags: List<String>,
    val status: JobStatus
)