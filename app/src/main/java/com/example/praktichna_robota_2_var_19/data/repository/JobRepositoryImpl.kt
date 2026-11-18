package com.example.praktichna_robota_2_var_19.data.repository

import android.text.Html
import android.util.Log
import com.example.praktichna_robota_2_var_19.data.remote.RemotiveJobDto
import com.example.praktichna_robota_2_var_19.data.remote.RetrofitInstance
import com.example.praktichna_robota_2_var_19.domain.model.Job
import com.example.praktichna_robota_2_var_19.domain.model.JobStatus
import com.example.praktichna_robota_2_var_19.domain.repository.JobRepository
import kotlinx.coroutines.CancellationException

class JobRepositoryImpl : JobRepository {

    override suspend fun getJobs(): List<Job> {

        return try {

            val response = RetrofitInstance.api.getJobs()

            response.jobs.orEmpty().mapNotNull { dto ->
                dto.toDomain()
            }

        } catch (e: CancellationException) {

            throw e

        } catch (e: Exception) {

            Log.e(
                "JobRepository",
                "Не вдалося завантажити вакансії",
                e
            )

            emptyList()
        }
    }
}

private fun RemotiveJobDto.toDomain(): Job? {

    val jobId = id ?: return null

    val jobTags = tags.orEmpty()

    val cleanDescription = Html.fromHtml(
        description.orEmpty(),
        Html.FROM_HTML_MODE_COMPACT
    ).toString().trim()

    return Job(
        id = jobId,

        title = title
            ?.takeIf { it.isNotBlank() }
            ?: "Назву не вказано",

        company = companyName
            ?.takeIf { it.isNotBlank() }
            ?: "Компанію не вказано",

        location = location
            ?.takeIf { it.isNotBlank() }
            ?: "Віддалена робота",

        salary = parseMonthlySalary(salary),

        tags = jobTags,

        status = JobStatus.OPEN,

        description = cleanDescription,

        experience = "Не вказано в оголошенні",

        employmentType = formatEmploymentType(jobType),

        requirements = jobTags.take(5),

        sourceUrl = url.orEmpty()
    )
}

private fun formatEmploymentType(type: String?): String {

    return when (type?.lowercase()) {

        "full_time" -> "Повна зайнятість"

        "part_time" -> "Неповна зайнятість"

        "contract" -> "Контракт"

        "freelance" -> "Фриланс"

        "internship" -> "Стажування"

        else -> "Не вказано"
    }
}

private fun parseMonthlySalary(value: String?): Double {

    if (value.isNullOrBlank()) {
        return 0.0
    }

    val normalized = value.lowercase()

    if (
        "€" in value ||
        "eur" in normalized ||
        "£" in value ||
        "gbp" in normalized
    ) {
        return 0.0
    }

    val numberText = Regex("""\d[\d,]*(?:\.\d+)?""")
        .find(value)
        ?.value
        ?: return 0.0

    val number = numberText
        .replace(",", "")
        .toDoubleOrNull()
        ?: return 0.0

    val amount = if (
        Regex("""\d+(?:\.\d+)?\s*k\b""")
            .containsMatchIn(normalized)
    ) {
        number * 1000
    } else {
        number
    }

    return when {

        "hour" in normalized || "/hr" in normalized ->
            amount * 160

        "month" in normalized || "/mo" in normalized ->
            amount

        "week" in normalized ->
            amount * 4.33

        else ->
            amount / 12
    }
}