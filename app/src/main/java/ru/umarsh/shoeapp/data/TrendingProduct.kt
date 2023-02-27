package ru.umarsh.shoeapp.data

import ru.umarsh.shoeapp.R

data class TrendingProduct(
    val name: String,
    val price: String,
    val image: Int
) {
    companion object {
        val list = listOf(
            TrendingProduct("Under Armour Phantom", "₹33000", R.drawable.armour_phantom),
            TrendingProduct("Under Armour 109", "₹19000", R.drawable.under_1031),
            TrendingProduct("Nike Air Max 270", "₹12000", R.drawable.adidas_1032),
            TrendingProduct("Adidas Neo", "₹15000", R.drawable.shoe_101),
            TrendingProduct("Roadster", "₹2300", R.drawable.roaster_100),
        )
    }
}
