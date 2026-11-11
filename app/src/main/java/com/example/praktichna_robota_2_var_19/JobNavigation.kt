
package com.example.praktichna_robota_2_var_19

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun JobNavigation() {

    val navController = rememberNavController()
    val listViewModel: ListViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {

        composable("list") {
            JobListScreen(
                viewModel = listViewModel,
                onJobClick = { id ->
                    navController.navigate("details/$id")
                }
            )
        }

        composable(
            route = "details/{itemId}",
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val itemId = backStackEntry.arguments?.getInt("itemId")

            val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

            val selectedJob = uiState.jobs.find { job ->
                job.id == itemId
            }

            if (selectedJob != null) {
                DetailScreen(
                    job = selectedJob,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                Text("Вакансію не знайдено")
            }
        }
    }
}
