package ru.umarsh.shoeapp.presentation.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.umarsh.shoeapp.R
import ru.umarsh.shoeapp.enum.HomeMenu
import ru.umarsh.shoeapp.enum.HomeMenuAction
import ru.umarsh.shoeapp.enum.MenuState
import ru.umarsh.shoeapp.presentation.cart.CartComponent
import ru.umarsh.shoeapp.presentation.dashboard.DashboardComponent
import ru.umarsh.shoeapp.presentation.favorite.FavoriteComponent
import ru.umarsh.shoeapp.presentation.notifications.NotificationsComponent
import ru.umarsh.shoeapp.presentation.profile.ProfileComponent
import ru.umarsh.shoeapp.presentation.settings.SettingsComponent
import ru.umarsh.shoeapp.ui.theme.textColor
import kotlin.math.roundToInt

@Composable
fun MainScreen(navController: NavController) {
    var screen by remember { mutableStateOf(HomeMenu.HOME.name) }
    var currentState by remember { mutableStateOf(MenuState.COLLAPSED) }
    val updateAnim = updateTransition(currentState, label = "MenuState")
    val scale = updateAnim.animateFloat(
        transitionSpec = {
            when {
                MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                    tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                }
                MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                    tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                }
                else -> snap()
            }
        }, label = ""
    ) {
        when (it) {
            MenuState.EXPANDED -> 0.7f
            MenuState.COLLAPSED -> 1f
        }
    }

    val transitionOffset = updateAnim.animateOffset(transitionSpec = {
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            else -> snap()
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> Offset(750f, 60f)
            MenuState.COLLAPSED -> Offset(0f, 0f)
        }
    }

    val alphaMenu = updateAnim.animateFloat(
        transitionSpec = {
            when {
                MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                    tween(durationMillis = 300)
                }
                MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                    tween(durationMillis = 300)
                }
                else -> snap()
            }
        }, label = ""
    ) {
        when (it) {
            MenuState.EXPANDED -> 1f
            MenuState.COLLAPSED -> 0.5f
        }
    }

    val roundness = updateAnim.animateDp({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(durationMillis = 300)
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(durationMillis = 300)
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> 20.dp
            MenuState.COLLAPSED -> 0.dp
        }
    }

    val menuOffset = updateAnim.animateOffset({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> Offset(0f, 0f)
            MenuState.COLLAPSED -> Offset(-10f, 0f)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF064789))
    ) {
        MenuComponent(
            modifier = Modifier
                .offset {
                    IntOffset(
                        menuOffset.value.x.roundToInt(),
                        menuOffset.value.y.roundToInt()
                    )
                }
                .alpha(alphaMenu.value)
        ) {
            when (it) {
                is HomeMenuAction.MenuSelected -> {
                    screen = it.menu.name
                }
                HomeMenuAction.SETTINGS -> {
                    screen = "SETTINGS"
                }
                HomeMenuAction.LOGOUT -> {
                    //do logout
                }
                else -> {
                    currentState = MenuState.COLLAPSED
                }
            }
            currentState = MenuState.COLLAPSED
        }
        // stack layer 0
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value - 0.05f)
                .offset {
                    IntOffset(
                        transitionOffset.value.x.toInt() - 50,
                        transitionOffset.value.y.toInt()
                    )
                }
                .background(Color(0xFFF3F6FA).copy(alpha = .90f), shape = RoundedCornerShape(20.dp))
                .padding(8.dp)
                .alpha(alphaMenu.value)
        )
        //stack layer 1
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value - 0.08f)
                .offset {
                    IntOffset(
                        transitionOffset.value.x.toInt() - 100,
                        transitionOffset.value.y.toInt()
                    )
                }
                .background(Color(0xFFF3F6FA).copy(.5f), shape = RoundedCornerShape(20.dp))
                .padding(8.dp)
                .alpha(alphaMenu.value)
        )
        // dashboard content
        Column(modifier = Modifier
            .fillMaxSize()
            .scale(scale.value)
            .offset {
                IntOffset(
                    transitionOffset.value.x.toInt(),
                    transitionOffset.value.y.toInt()
                )
            }
            .clip(shape = RoundedCornerShape(roundness.value))
            .background(color = Color(0xFFebf2fa))) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            currentState = when (currentState) {
                                MenuState.EXPANDED -> MenuState.COLLAPSED
                                MenuState.COLLAPSED -> MenuState.EXPANDED
                            }
                        },
                    colorFilter = ColorFilter.tint(textColor)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = screen,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
            when (screen) {
                HomeMenu.HOME.name -> {
                    DashboardComponent(navController)
                }
                HomeMenu.PROFILE.name -> {
                    ProfileComponent()
                }
                HomeMenu.CART.name -> {
                    CartComponent()
                }
                HomeMenu.FAVORITE.name -> {
                    FavoriteComponent()
                }
                HomeMenu.NOTIFICATION.name -> {
                    NotificationsComponent()
                }
                "SETTINGS" -> {
                    SettingsComponent()
                }
            }
        }
    }
}