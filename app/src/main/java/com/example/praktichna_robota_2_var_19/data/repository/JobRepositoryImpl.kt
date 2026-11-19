package com.example.praktichna_robota_2_var_19.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.praktichna_robota_2_var_19.data.local.AppDatabase
import com.example.praktichna_robota_2_var_19.data.mapper.toDomain
import com.example.praktichna_robota_2_var_19.data.mapper.toEntity
import com.example.praktichna_robota_2_var_19.data.repository.remote.JobApiService
import com.example.praktichna_robota_2_var_19.domain.model.Job
import com.example.praktichna_robota_2_var_19.domain.model.Result
import com.example.praktichna_robota_2_var_19.domain.repository.JobRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class JobRepositoryImpl(
    private val api: JobApiService,
    private val database: AppDatabase
) : JobRepository {

    private val jobDao = database.jobDao()

    override fun observeJobs(): Flow<List<Job>> {

        return jobDao.getAll().map { entities ->

            entities.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun refresh(): Result<Unit> {

        return try {

            val response = api.getJobs()

            val remoteJobs = response.jobs
                ?: throw IOException("Сервер не повернув список вакансій")

            val localJobs = remoteJobs.mapNotNull { dto ->
                dto.toEntity()
            }

            database.withTransaction {

                jobDao.deleteAll()

                if (localJobs.isNotEmpty()) {
                    jobDao.insertAll(localJobs)
                }
            }

            Result.Success(Unit)

        } catch (e: CancellationException) {

            throw e

        } catch (e: Exception) {

            Log.e(
                "JobRepository",
                "Помилка синхронізації вакансій",
                e
            )

            Result.Error(
                "Немає з'єднання. Показано збережені дані."
            )
        }
    }
}