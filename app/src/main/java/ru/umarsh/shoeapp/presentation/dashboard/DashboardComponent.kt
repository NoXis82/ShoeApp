package ru.umarsh.shoeapp.presentation.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.umarsh.shoeapp.enum.DashboardOptions

@Composable
fun DashboardComponent() {
    val context = LocalContext.current
    Column(modifier = Modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(DashboardOptions.values()) {
                ProjectItem(it) {
                    when (it) {
                        DashboardOptions.SHOE_APP -> {
                            Toast.makeText(context, "SHOE_APP", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }
}