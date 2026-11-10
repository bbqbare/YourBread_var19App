package com.example.praktichna_robota_2_var_19

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktichna_robota_2_var_19.ui.theme.Praktichna_robota_2_var_19Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Praktichna_robota_2_var_19Theme {
                JobListScreen()
            }
        }
    }
}

@Composable
fun JobListScreen(
    viewModel: ListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val filteredJobs = uiState.jobs.filter { job ->
        job.title.contains(uiState.searchText, ignoreCase = true) ||
                job.company.contains(uiState.searchText, ignoreCase = true) ||
                job.location.contains(uiState.searchText, ignoreCase = true)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "Пошук роботи",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Знайди вакансію, яка тобі підходить",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = uiState.searchText,
                onValueChange = { text ->
                    viewModel.onSearchTextChange(text)
                },
                label = {
                    Text("Назва вакансії, компанія або місто")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Вакансії (${filteredJobs.size})",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = filteredJobs,
                    key = { it.id }
                ) { job ->

                    val statusText = when (job.status) {
                        JobStatus.OPEN -> "Відкрита"
                        JobStatus.URGENT -> "Термінова"
                        JobStatus.CLOSED -> "Закрита"
                    }

                    val statusColor = when (job.status) {
                        JobStatus.OPEN -> Color(0xFF2E7D32)
                        JobStatus.URGENT -> Color(0xFFE65100)
                        JobStatus.CLOSED -> Color(0xFF757575)
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            Text(
                                text = job.title,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = job.company,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Місце роботи: ${job.location}",
                                fontSize = 14.sp
                            )

                            Text(
                                text = "Зарплата: $${job.salary.toInt()} / місяць",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Surface(
                                color = statusColor.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = statusText,
                                    color = statusColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 6.dp
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Навички: ${job.tags.joinToString(", ")}",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "${job.title} - ${job.company}\n" +
                                                    "Зарплата: $${job.salary.toInt()}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                ) {
                                    Text("Детальніше")
                                }
                            }
                        }
                    }
                }

                if (filteredJobs.isEmpty()) {
                    item {
                        Text(
                            text = "Вакансій не знайдено",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }
                }
            }
        }
    }
}