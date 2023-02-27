package ru.umarsh.shoeapp.presentation.shoe_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.umarsh.shoeapp.data.TrendingProduct
import ru.umarsh.shoeapp.ui.theme.textColor
import ru.umarsh.shoeapp.R
import ru.umarsh.shoeapp.ui.theme.accentColor

@Composable
fun TrendingProductItem(product: TrendingProduct) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(190.dp)
            .padding(start = 16.dp, end = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "shoe",
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.price,
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "fav",
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_ribbon),
                contentDescription = "fav",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 4.dp, y = (-4).dp)
                    .clip(RoundedCornerShape(8.dp)),
                colorFilter = ColorFilter.tint(accentColor),
                contentScale = ContentScale.Crop
            )
        }
    }
}