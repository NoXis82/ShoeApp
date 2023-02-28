package ru.umarsh.shoeapp.presentation.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.umarsh.shoeapp.R
import ru.umarsh.shoeapp.data.CartDataModel
import ru.umarsh.shoeapp.ui.theme.accentColor
import ru.umarsh.shoeapp.ui.theme.textColor

@Composable
fun CartScreen(
    navController: NavController
) {
    val listItems = remember { mutableStateOf(CartDataModel.list) }
    val context = LocalContext.current

    Surface(color = MaterialTheme.colorScheme.surface) {
        Box {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(16.dp)
                        .rotate(180f)
                        .size(24.dp)
                        .clickable {
                            navController.navigateUp()
                        }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "My Bag",
                        fontWeight = FontWeight.Bold,
                        fontSize = 34.sp,
                        color = textColor
                    )
                    Text(
                        text = "Total ${listItems.value.size} items",
                        color = textColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray.copy(alpha = 0.5f))
                )
                LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    items(listItems.value) { item ->
                        CartItem(item) {
                            listItems.value = listItems.value.filter { it.id != item.id }
                            Toast.makeText(context, "Item removed ${it.name}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    item { Spacer(modifier = Modifier.height(100.dp)) }
                }
            }
            Text(
                text = "No Items in your cart!",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .alpha(if (listItems.value.isEmpty()) 1f else 0f),
                color = textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.surface)
                    .height(120.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray.copy(alpha = 0.5f))
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Total",
                        color = textColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "₹${listItems.value.sumOf { it.price }}",
                        color = textColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        Toast.makeText(context, "Opening Checkout", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor)
                ) {
                    Text(text = "Go To Checkout", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}