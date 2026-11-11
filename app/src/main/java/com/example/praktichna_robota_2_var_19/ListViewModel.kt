package com.example.praktichna_robota_2_var_19

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktichna_robota_2_var_19.data.repository.JobRepositoryImpl
import com.example.praktichna_robota_2_var_19.domain.usecase.GetJobsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val getJobsUseCase: GetJobsUseCase =
        GetJobsUseCase(JobRepositoryImpl())
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadJobs()
    }

    private fun loadJobs() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true
            )

            val jobs = getJobsUseCase()

            _uiState.value = _uiState.value.copy(
                jobs = jobs,
                isLoading = false
            )
        }
    }

    fun onSearchTextChange(text: String) {
        _uiState.value = _uiState.value.copy(
            searchText = text
        )
    }
}