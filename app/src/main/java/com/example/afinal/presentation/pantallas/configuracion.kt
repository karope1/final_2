package com.example.afinal.presentation.pantallas

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText

@Composable
fun ConfiguracionScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val locale = configuration.locales[0]
    val displayMetrics = configuration
    val androidVersion = Build.VERSION.RELEASE
    val deviceModel = Build.MODEL
    val manufacturer = Build.MANUFACTURER
    val resolution = "${displayMetrics.screenWidthDp} x ${displayMetrics.screenHeightDp}"

    Scaffold(
        timeText = { TimeText() },
        positionIndicator = {},
        modifier = Modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item {
                Text("Información del reloj", color = Color.White, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
            }
            item {
                Text("Modelo: $manufacturer $deviceModel", color = Color.White, fontSize = 10.sp)
            }
            item {
                Text("Android: $androidVersion", color = Color.White, fontSize = 10.sp)
            }
            item {
                Text("Idioma: ${locale.displayLanguage}", color = Color.White, fontSize = 10.sp)
            }
            item {
                Text("Resolución: $resolution", color = Color.White, fontSize = 10.sp)
            }
        }
    }
}