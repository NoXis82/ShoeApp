package ru.umarsh.shoeapp.enum

import ru.umarsh.shoeapp.R

enum class DashboardOptions(val title: String, val image: Int) {
    SHOE_APP("Shoe App", R.drawable.shoe_background),
    PIZZA_APP("Pizza app", R.drawable.pizza_background),
    UPCOMING_APP("Upcoming App", R.drawable.upcoming_background);
}