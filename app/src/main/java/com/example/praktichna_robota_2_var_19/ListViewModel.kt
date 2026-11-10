package com.example.praktichna_robota_2_var_19

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ListUiState(
            jobs = listOf(
                Job(1, "Android Developer", "TechCorp", "Kyiv", 3500.0,
                    listOf("Kotlin", "Android"), JobStatus.OPEN),

                Job(2, "Junior QA Engineer", "BugHunters", "Remote", 2500.0,
                    listOf("Manual", "Jira"), JobStatus.OPEN),

                Job(3, "Product Manager", "InnoGroup", "Kyiv", 4500.0,
                    listOf("Agile", "Management"), JobStatus.CLOSED),

                Job(4, "Senior Android Dev", "MobileFirst", "Remote", 5500.0,
                    listOf("Kotlin", "Compose"), JobStatus.URGENT),

                Job(5, "UI/UX Designer", "DesignHub", "Odesa", 2000.0,
                    listOf("Figma", "UX"), JobStatus.OPEN),

                Job(6, "DevOps Engineer", "CloudScale", "Kyiv", 4800.0,
                    listOf("Docker", "Kubernetes"), JobStatus.OPEN),

                Job(7, "Java Developer", "SoftLine", "Lviv", 4000.0,
                    listOf("Java", "Spring"), JobStatus.URGENT),

                Job(8, "Frontend Developer", "WebStudio", "Remote", 3200.0,
                    listOf("HTML", "CSS", "React"), JobStatus.CLOSED)
            )
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onSearchTextChange(text: String) {
        _uiState.value = _uiState.value.copy(
            searchText = text
        )
    }
}