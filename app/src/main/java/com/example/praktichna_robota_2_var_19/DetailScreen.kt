package com.example.praktichna_robota_2_var_19

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktichna_robota_2_var_19.domain.model.Job
import com.example.praktichna_robota_2_var_19.domain.model.JobStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    job: Job,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Деталі вакансії")
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Назад")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = job.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = job.company,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Місце роботи: ${job.location}",
                fontSize = 16.sp
            )

            Text(
                text = "Зарплата: $${job.salary.toInt()} / місяць",
                fontSize = 16.sp
            )

            Text(
                text = "Статус: ${
                    when (job.status) {
                        JobStatus.OPEN -> "Відкрита"
                        JobStatus.URGENT -> "Термінова"
                        JobStatus.CLOSED -> "Закрита"
                    }
                }",
                fontSize = 16.sp
            )

            Text(
                text = "Навички: ${job.tags.joinToString(", ")}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Опис вакансії",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = job.description,
                fontSize = 15.sp
            )

            Text(
                text = "Досвід роботи",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = job.experience,
                fontSize = 15.sp
            )

            Text(
                text = "Тип зайнятості",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = job.employmentType,
                fontSize = 15.sp
            )

            Text(
                text = "Вимоги до кандидата",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            job.requirements.forEach { requirement ->
                Text(
                    text = "• $requirement",
                    fontSize = 15.sp
                )
            }
        }
    }
}