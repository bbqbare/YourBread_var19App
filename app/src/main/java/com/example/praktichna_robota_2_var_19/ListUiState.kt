package com.example.praktichna_robota_2_var_19

import com.example.praktichna_robota_2_var_19.domain.model.Job

data class ListUiState(
    val jobs: List<Job> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false
)