package ru.umarsh.shoeapp.presentation.shoe_app

import android.app.Activity
import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.umarsh.shoeapp.R
import ru.umarsh.shoeapp.data.CarouselDataModel
import ru.umarsh.shoeapp.enum.DetailState
import ru.umarsh.shoeapp.ui.theme.accentColor
import ru.umarsh.shoeapp.util.Screen

@Composable
fun DetailComponent(
    carouselDataModel: CarouselDataModel = CarouselDataModel.listOfShoes.first(),
    viewModel: MainViewModel,
    navController: NavController
) {

    Log.e("MY_TAG", ">>> $carouselDataModel")

    var currentState by remember { mutableStateOf(DetailState.COLLAPSED) }
    val buttonState by remember { viewModel.buttonState }

    val transition = updateTransition(currentState, label = "")
    val context = LocalContext.current

    LaunchedEffect(key1 = currentState) {
        currentState = DetailState.EXPANDED
        val activity = context as Activity
        activity.window.statusBarColor = carouselDataModel.color.toArgb()

        viewModel.cartFlow.collect {
            if (it) {
                navController.navigate(Screen.CartScreen.route)
            }
        }
    }

    val size by transition.animateDp(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                spring(stiffness = 100f, dampingRatio = 0.5f)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            0.dp
        } else {
            300.dp
        }
    }

    val circleSize by transition.animateFloat(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                spring(stiffness = 100f, dampingRatio = 0.5f)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            0f
        } else {
            1.6f
        }
    }

    val color by transition.animateColor(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            Color.Transparent
        } else {
            carouselDataModel.color
        }
    }

    val translateY by transition.animateOffset(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            Offset(0f, 100f)
        } else {
            Offset(0f, 0f)
        }
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            0f
        } else {
            1f
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .graphicsLayer {
                        translationX = 180f
                        translationY = -350f
                        scaleX = circleSize
                    }
                    .background(shape = CircleShape, color = color))
                DetailsToolbar(carouselDataModel, viewModel)
                Image(
                    painter = painterResource(id = carouselDataModel.resId),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .align(Alignment.Center)
                        .rotate(330f)
                        .size(size)
                )

                //todo



            }
        }
    }
}

@Composable
fun DetailsToolbar(carouselDataModel: CarouselDataModel, viewModel: MainViewModel) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "back",
            modifier = Modifier
                .rotate(180f)
                .size(24.dp)
                .clickable {
                    val activity = context as Activity
                    activity.window.statusBarColor = accentColor.toArgb()
                    viewModel.onBackClick()
                },
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            text = carouselDataModel.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )

        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "back",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }

}
