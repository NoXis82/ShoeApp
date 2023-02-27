package ru.umarsh.shoeapp.presentation.shoe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.umarsh.shoeapp.R
import ru.umarsh.shoeapp.enum.BottomNav
import ru.umarsh.shoeapp.ui.theme.accentColor
import ru.umarsh.shoeapp.ui.theme.textColor

@Composable
fun DashboardComponent(viewModel: MainViewModel) {
    val screen = remember { mutableStateOf(BottomNav.Home) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeToolbar()
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screen.value) {
                BottomNav.Home -> HomeComponent(viewModel)
                else -> {
                    Text(
                        text = "Coming Soon",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = textColor
                    )
                }
            }
        }
        BottomToolbar(screen)
    }
}

@Composable
fun BottomToolbar(screen: MutableState<BottomNav>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNav.values().forEach { nav ->
            Image(
                painter = painterResource(id = nav.icon),
                contentDescription = "search",
                modifier = Modifier
                    .padding(4.dp)
                    .size(36.dp)
                    .padding(6.dp)
                    .clickable {
                        screen.value = nav
                    },
                colorFilter = if (screen.value == nav) {
                    ColorFilter.tint(accentColor)
                } else {
                    ColorFilter.tint(Color.LightGray)
                }
            )
        }
    }
}

@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Discover",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = textColor
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search",
            modifier = Modifier
                .padding(6.dp)
                .size(32.dp)
                .background(color = Color.LightGray.copy(alpha = .2f), shape = CircleShape)
                .padding(8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "search",
            modifier = Modifier
                .padding(6.dp)
                .size(32.dp)
                .background(color = Color.LightGray.copy(alpha = .2f), shape = CircleShape)
                .padding(8.dp)
        )
    }
}