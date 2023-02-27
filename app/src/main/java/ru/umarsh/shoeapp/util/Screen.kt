package ru.umarsh.shoeapp.util

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object ShoeAppScreen : Screen("shoe_app_screen")
    object CartScreen : Screen("cart_screen")
}

