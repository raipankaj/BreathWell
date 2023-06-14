package com.alltechies.breathwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alltechies.breathwell.screens.Dashboard
import com.alltechies.breathwell.screens.ScheduleTimer
import com.alltechies.breathwell.ui.theme.BreathWellTheme
import com.alltechies.breathwell.viewmodel.BreathViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreathWellTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenNavigation()
                }
            }
        }
    }
}


@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            val breathViewModel: BreathViewModel = viewModel()
            Dashboard(
                breathViewModel = breathViewModel,
                onClick = {
                    navController.navigate("timer/$it")
                }
            )
        }

        composable(
            "timer/{type}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType }
            )
        ) { backstackEntry ->

            val breathViewModel: BreathViewModel = viewModel()
            val type: String? = backstackEntry.arguments?.getString("type")

            ScheduleTimer(
                breathViewModel = breathViewModel,
                type = type,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}
