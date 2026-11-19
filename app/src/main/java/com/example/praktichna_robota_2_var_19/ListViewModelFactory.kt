package com.example.praktichna_robota_2_var_19

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.praktichna_robota_2_var_19.data.local.AppDatabase
import com.example.praktichna_robota_2_var_19.data.repository.JobRepositoryImpl
import com.example.praktichna_robota_2_var_19.data.repository.remote.RetrofitInstance
import com.example.praktichna_robota_2_var_19.domain.usecase.GetJobsUseCase

class ListViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {

    private val appContext = context.applicationContext

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {

            val database = AppDatabase.getDatabase(appContext)

            val repository = JobRepositoryImpl(
                api = RetrofitInstance.api,
                database = database
            )

            val useCase = GetJobsUseCase(repository)

            @Suppress("UNCHECKED_CAST")
            return ListViewModel(useCase) as T
        }

        throw IllegalArgumentException(
            "Невідомий клас ViewModel"
        )
    }
}