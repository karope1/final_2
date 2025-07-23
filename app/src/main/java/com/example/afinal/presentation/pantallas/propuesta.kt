package com.example.afinal.presentation.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.example.afinal.presentation.BaseScreen
import com.example.afinal.presentation.DarkBlue
import com.example.afinal.presentation.LightText
import com.example.afinal.presentation.MediumBlue


// Pantalla 13 - Sueño
@Composable
fun PantallaTrece(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Sueño",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Domingo",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MediumBlue
                        )
                    )
                    Text(
                        text = "20-21 enero",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "66% eficiencia",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(
                        text = "8 h 15 m",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MediumBlue
                        )
                    )
                    Text(
                        text = "Tiempo en la cama",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "01:12 Habla durante el sueño",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Despertares:",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )
                Text(
                    text = "Sueño ligero | Sueño profundo",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(DarkBlue)
                ) {
                    Text(
                        text = "Gráfico de sueño",
                        color = LightText,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("00", "01", "02", "03", "04", "05", "06").forEach { hour ->
                        Text(
                            text = hour,
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = LightText
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ronquidos: @de",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    )
                )
            }
        }

    }
}

// Pantalla 14 - Sonidos
@Composable
fun PantallaCatorce(onPrev: () -> Unit) {
    BaseScreen(
        title = "",
    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Sonidos seleccionados",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "Habla durante el sueño",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)) {
                    Text(text = "- 0:9:00", style = TextStyle(fontSize = 14.sp, color = LightText))
                    Text(text = "- 0:9:04", style = TextStyle(fontSize = 14.sp, color = LightText))
                    Text(text = "- 0:9:38", style = TextStyle(fontSize = 14.sp, color = LightText))
                }

                Text(
                    text = "Alboroto o libro de bebé",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)) {
                    Text(text = "- 01:42", style = TextStyle(fontSize = 14.sp, color = LightText))
                    Text(text = "- 02:21", style = TextStyle(fontSize = 14.sp, color = LightText))
                    Text(text = "- 02:21", style = TextStyle(fontSize = 14.sp, color = LightText))
                    Text(text = "- 03:07", style = TextStyle(fontSize = 14.sp, color = LightText))
                }

                Text(
                    text = "Ronquidos",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onPrev,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MediumBlue,
                        contentColor = LightText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text("Regresar")
                }
            }
        }
    }

}