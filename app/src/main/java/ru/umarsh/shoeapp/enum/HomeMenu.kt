package ru.umarsh.shoeapp.enum

import ru.umarsh.shoeapp.R

enum class HomeMenu(val title: String, val icon: Int) {
    HOME("Home", R.drawable.ic_home),
    NOTIFICATION("Notification", R.drawable.ic_notification),
    FAVORITE("Favorite", R.drawable.ic_heart),
    CART("Cart", R.drawable.ic_shop),
    PROFILE("Profile", R.drawable.ic_profile);
}

sealed class HomeMenuAction {
    object CLOSE : HomeMenuAction()
    object LOGOUT : HomeMenuAction()
    object SETTINGS : HomeMenuAction()
    data class MenuSelected(val menu: HomeMenu) : HomeMenuAction()
}