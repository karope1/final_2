package com.example.afinal.presentation.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import kotlinx.coroutines.delay
import kotlin.random.Random
import com.example.afinal.R

@Composable
fun TapTheDotGame(navController: NavController) {
    var dotVisible by remember { mutableStateOf(true) }
    var score by remember { mutableStateOf(0) }
    var dotPosition by remember { mutableStateOf(Pair(0f, 0f)) }
    var screenWidth by remember { mutableStateOf(0f) }
    var screenHeight by remember { mutableStateOf(0f) }

    val density = LocalDensity.current

    LaunchedEffect(dotVisible) {
        if (dotVisible) {
            delay(1500) // Tiempo límite para tocar
            dotVisible = false
        } else {
            delay(500)
            dotPosition = Pair(
                Random.nextFloat() * (screenWidth - 60f),
                Random.nextFloat() * (screenHeight - 60f)
            )
            dotVisible = true
        }
    }

    Scaffold(
        timeText = { TimeText() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .onGloballyPositioned {
                    screenWidth = it.size.width.toFloat()
                    screenHeight = it.size.height.toFloat()
                }
        ) {
            if (dotVisible) {
                Image(
                    painter = painterResource(id = R.drawable.outline_currency_rupee_circle_24),
                    contentDescription = "Dot",
                    modifier = Modifier
                        .size(60.dp)
                        .absoluteOffset(
                            x = with(density) { dotPosition.first.toDp() },
                            y = with(density) { dotPosition.second.toDp() }
                        )
                        .clickable {
                            dotVisible = false
                            score++
                        }
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                androidx.wear.compose.material.Text(
                    text = "Puntos: $score",
                    color = Color.White
                )
            }
        }
    }
}

