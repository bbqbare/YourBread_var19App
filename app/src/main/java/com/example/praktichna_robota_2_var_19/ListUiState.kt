package com.example.praktichna_robota_2_var_19

data class ListUiState(
    val jobs: List<Job> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false
)