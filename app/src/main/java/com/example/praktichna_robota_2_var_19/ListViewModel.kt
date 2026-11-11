package com.example.praktichna_robota_2_var_19

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ListUiState(
            jobs = listOf(
                Job(
                    101, "Android Developer", "TechCorp", "Kyiv",
                    3500.0, listOf("Kotlin", "Android"), JobStatus.OPEN,
                    description = "Розробка та підтримка мобільних застосунків для Android.",
                    experience = "Від 1 року",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Знання Kotlin",
                        "Досвід роботи з Android Studio",
                        "Розуміння Jetpack Compose"
                    )
                ),

                Job(
                    102, "Junior QA Engineer", "BugHunters", "Remote",
                    2500.0, listOf("Manual", "Jira"), JobStatus.OPEN,
                    description = "Тестування програмного забезпечення та пошук помилок.",
                    experience = "Без досвіду або до 1 року",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Уважність до деталей",
                        "Знання основ тестування",
                        "Навички роботи з Jira"
                    )
                ),

                Job(
                    103, "Product Manager", "InnoGroup", "Kyiv",
                    4500.0, listOf("Agile", "Management"), JobStatus.CLOSED,
                    description = "Організація роботи команди та планування розвитку продукту.",
                    experience = "Від 2 років",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Знання Agile",
                        "Навички управління командою",
                        "Вміння планувати завдання"
                    )
                ),

                Job(
                    104, "Senior Android Dev", "MobileFirst", "Remote",
                    5500.0, listOf("Kotlin", "Compose"), JobStatus.URGENT,
                    description = "Розробка складних Android-застосунків та оптимізація їх роботи.",
                    experience = "Від 3 років",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Впевнене знання Kotlin",
                        "Досвід роботи з Jetpack Compose",
                        "Знання архітектури Android"
                    )
                ),

                Job(
                    105, "UI/UX Designer", "DesignHub", "Odesa",
                    2000.0, listOf("Figma", "UX"), JobStatus.OPEN,
                    description = "Створення зручних інтерфейсів та дизайну мобільних і вебзастосунків.",
                    experience = "Від 1 року",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Знання Figma",
                        "Розуміння UX/UI",
                        "Наявність портфоліо"
                    )
                ),

                Job(
                    106, "DevOps Engineer", "CloudScale", "Kyiv",
                    4800.0, listOf("Docker", "Kubernetes"), JobStatus.OPEN,
                    description = "Налаштування серверної інфраструктури та автоматизація розгортання програм.",
                    experience = "Від 2 років",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Знання Docker",
                        "Досвід роботи з Kubernetes",
                        "Розуміння CI/CD"
                    )
                ),

                Job(
                    107, "Java Developer", "SoftLine", "Lviv",
                    4000.0, listOf("Java", "Spring"), JobStatus.URGENT,
                    description = "Розробка серверної частини програмного забезпечення на Java.",
                    experience = "Від 2 років",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Знання Java",
                        "Досвід роботи зі Spring",
                        "Розуміння SQL"
                    )
                ),

                Job(
                    108, "Frontend Developer", "WebStudio", "Remote",
                    3200.0, listOf("HTML", "CSS", "React"), JobStatus.CLOSED,
                    description = "Створення та підтримка користувацьких інтерфейсів вебсайтів.",
                    experience = "Від 1 року",
                    employmentType = "Повна зайнятість",
                    requirements = listOf(
                        "Знання HTML та CSS",
                        "Знання JavaScript",
                        "Досвід роботи з React"
                    )
                )
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