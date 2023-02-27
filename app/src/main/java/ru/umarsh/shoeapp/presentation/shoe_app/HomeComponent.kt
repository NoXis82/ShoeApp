package ru.umarsh.shoeapp.presentation.shoe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.umarsh.shoeapp.R
import ru.umarsh.shoeapp.data.CarouselDataModel
import ru.umarsh.shoeapp.data.TrendingProduct
import ru.umarsh.shoeapp.ui.theme.textColor
import kotlin.math.absoluteValue

@Composable
fun HomeComponent(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeTopComponent(viewModel)
        HomeMiddleComponent()
        HomeBottomComponent()
    }
}

@Composable
fun HomeBottomComponent() {
    LazyRow(state = rememberLazyListState()) {
        items(TrendingProduct.list.size) { index ->
            TrendingProductItem(TrendingProduct.list[index])
        }
    }
}

@Composable
fun HomeMiddleComponent() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Favorite",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = textColor
        )
        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "more"
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTopComponent(viewModel: MainViewModel) {
    val pagerState = rememberPagerState()
    val selectedCategory = remember { mutableStateOf(CarouselDataModel.categories.size - 1) }
    val rememberScope = rememberCoroutineScope()

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.width(64.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarouselDataModel.categories.forEachIndexed { index, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .height(90.dp)
                        .graphicsLayer {
                            rotationZ = -90f
                            translationX = 100f
                        }
                        .clickable {
                            selectedCategory.value = index
                            rememberScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedCategory.value == index) textColor else Color.LightGray,
                    maxLines = 1,
                )
            }
        }
        HorizontalPager(
            count = CarouselDataModel.listOfShoes.size,
            contentPadding = PaddingValues(end = 70.dp),
            state = pagerState
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            ShoeItem(shoe = CarouselDataModel.listOfShoes[page], pageOffset, viewModel)
        }
    }
}
