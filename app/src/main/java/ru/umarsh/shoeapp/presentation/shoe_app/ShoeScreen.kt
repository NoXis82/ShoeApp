package ru.umarsh.shoeapp.presentation.shoe_app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ShoeScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val screenState by remember { viewModel.screenState }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background
    ) {
        Crossfade(targetState = screenState) { state ->
            when (state) {
                is MainViewModel.UiState.Home -> {
                    DashboardComponent(viewModel)
                }
                is MainViewModel.UiState.Details -> {
                    DetailComponent(
                        state.carouselDataModel,
                        viewModel,
                        navController
                    )
                }
            }
        }
    }
}