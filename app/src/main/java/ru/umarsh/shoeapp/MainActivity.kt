package ru.umarsh.shoeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.umarsh.shoeapp.presentation.cart.CartScreen
import ru.umarsh.shoeapp.presentation.home.MainScreen
import ru.umarsh.shoeapp.presentation.shoe_app.MainViewModel
import ru.umarsh.shoeapp.presentation.shoe_app.ShoeScreen
import ru.umarsh.shoeapp.ui.theme.ShoeAppTheme
import ru.umarsh.shoeapp.util.Screen

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                        composable(route = Screen.ShoeAppScreen.route) {
                            ShoeScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(route = Screen.CartScreen.route) {
                            CartScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}