package ru.umarsh.shoeapp.presentation.cart

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.umarsh.shoeapp.data.CartDataModel
import ru.umarsh.shoeapp.ui.theme.textColor
import ru.umarsh.shoeapp.R

@Composable
fun CartItem(cartDataModel: CartDataModel, onRemove: (CartDataModel) -> Unit) {
    val itemCount = remember { mutableStateOf(1) }
    Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth()
            .animateContentSize()
            .padding(start = 16.dp, end = 8.dp, top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        color = Color.LightGray.copy(alpha = .4f),
                        shape = RoundedCornerShape(30.dp)
                    )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = cartDataModel.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = textColor,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "₹${cartDataModel.price}",
                    color = textColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "right arrow",
                        modifier = Modifier
                            .width(40.dp)
                            .height(30.dp)
                            .background(
                                color = Color.LightGray.copy(alpha = .2f),
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(4.dp)
                            .clickable {
                                itemCount.value--
                                if (itemCount.value <= 0) {
                                    onRemove(cartDataModel)
                                }
                            },
                        colorFilter = ColorFilter.tint(textColor.copy(alpha = .8f))
                    )
                    Text(
                        text = "${cartDataModel.quantity}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "right arrow",
                        modifier = Modifier
                            .width(40.dp)
                            .height(30.dp)
                            .background(
                                color = Color.LightGray.copy(alpha = .2f),
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(4.dp)
                            .clickable { itemCount.value++ },
                        colorFilter = ColorFilter.tint(textColor.copy(alpha = .8f))
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = cartDataModel.image),
            contentDescription = "bag",
            modifier = Modifier
                .size(150.dp)
                .rotate(20f)
                .offset(x = (-20).dp, y = (-5).dp)
        )
    }
}