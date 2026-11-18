package com.example.praktichna_robota_2_var_19.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(

    @PrimaryKey
    val id: Int,

    val title: String,

    val company: String,

    val location: String,

    val salary: Double,

    val tags: List<String>,

    val status: String,

    val description: String,

    val experience: String,

    val employmentType: String,

    val requirements: List<String>,

    val sourceUrl: String
)