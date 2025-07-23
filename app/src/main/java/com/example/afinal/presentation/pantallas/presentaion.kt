package com.example.afinal.presentation.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.example.afinal.R

@Composable
fun Presentacion(navController: NavController) {
    val listState = rememberScalingLazyListState()
    val currentTime = remember { mutableStateOf(LocalDateTime.now()) }

    // Actualización cada segundo
    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = LocalDateTime.now()
            delay(1000L) // Actualiza cada segundo
        }
    }

    // Formateadores
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM yyyy", Locale.getDefault())
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    Scaffold(
        //timeText = { /* Eliminado para personalizar */ },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
        modifier = Modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            // Imagen circular
            item {
                Image(
                    painter = painterResource(id = R.drawable.outline_person_24), // tu imagen de avatar
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
            }

            // Día y fecha
            item {
                Text(
                    text = currentTime.value.format(dateFormatter),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Hora con segundos
            item {
                Text(
                    text = currentTime.value.format(timeFormatter),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }

            // Derechos de autor
            item {
                Text(
                    text = "© KRP",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

