package com.example.praktichna_robota_2_var_19

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktichna_robota_2_var_19.domain.model.Result
import com.example.praktichna_robota_2_var_19.domain.usecase.GetJobsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val getJobsUseCase: GetJobsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())

    val uiState = _uiState.asStateFlow()

    init {
        observeJobs()
        refreshJobs()
    }

    private fun observeJobs() {

        viewModelScope.launch {

            getJobsUseCase.observeJobs().collect { jobs ->

                _uiState.value = _uiState.value.copy(
                    jobs = jobs
                )
            }
        }
    }

    fun refreshJobs() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            when (val result = getJobsUseCase.refresh()) {

                is Result.Success -> {

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = null
                    )
                }

                is Result.Error -> {

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {

        _uiState.value = _uiState.value.copy(
            searchText = text
        )
    }
}