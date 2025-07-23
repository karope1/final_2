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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.afinal.presentation.BaseScreen
import com.example.afinal.presentation.DarkBlue
import com.example.afinal.presentation.LightText
import com.example.afinal.presentation.MediumBlue
import com.example.afinal.presentation.NavigationButtons
import kotlin.math.sqrt


// Pantalla 1
@Composable
fun PantallaUno(onNext: () -> Unit) {
    BaseScreen(
        title = "",
    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ESTILO MILENIAL",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "⋆⋆⋆⋆⋆",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Bienvenida a tu experiencia de estilo personalizado",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MediumBlue,
                        contentColor = LightText
                    ),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("COMENZAR")
                }
            }
        }

    }

}

// Pantalla 2 - Calculadora
@Composable
fun PantallaDos(onNext: () -> Unit, onPrev: () -> Unit) {
    var display by remember { mutableStateOf("0") }
    var operation by remember { mutableStateOf(CalculatorOperation()) }

    fun handleButtonClick(button: String) {
        when {
            button in "0123456789" -> {
                display = if (display == "0") button else display + button
            }
            button == "." && !display.contains(".") -> {
                display += button
            }
            button == "C" -> {
                display = "0"
                operation = CalculatorOperation()
            }
            button == "+/-" -> {
                display = if (display.startsWith("-")) {
                    display.substring(1)
                } else {
                    "-$display"
                }
            }
            button == "=" -> {
                operation.secondOperand = display.toDoubleOrNull() ?: 0.0
                display = when (operation.operator) {
                    "+" -> (operation.firstOperand + operation.secondOperand).toString()
                    "-" -> (operation.firstOperand - operation.secondOperand).toString()
                    "*" -> (operation.firstOperand * operation.secondOperand).toString()
                    "/" -> if (operation.secondOperand != 0.0) {
                        (operation.firstOperand / operation.secondOperand).toString()
                    } else "Error"
                    else -> display
                }
                operation = CalculatorOperation()
            }
            button in "+-*/" -> {
                operation.firstOperand = display.toDoubleOrNull() ?: 0.0
                operation.operator = button
                display = "0"
            }
            button == "√" -> {
                val value = display.toDoubleOrNull() ?: 0.0
                display = if (value >= 0) sqrt(value).toString() else "Error"
            }
            button == "X2" -> {
                val value = display.toDoubleOrNull() ?: 0.0
                display = (value * value).toString()
            }
        }
    }

    BaseScreen(
        title = "Calculadora",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Text(
                text = display,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightText
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            CalculatorButtonRow(listOf("7", "8", "9", "/", "C")) { handleButtonClick(it) }

        }
        item{
            CalculatorButtonRow(listOf("4", "5", "6", "*", "+/-")) { handleButtonClick(it) }

        }
        item {
            CalculatorButtonRow(listOf("1", "2", "3", "-", "X2")) { handleButtonClick(it) }

        }
        item {
            CalculatorButtonRow(listOf("0", ".", "=", "+", "√")) { handleButtonClick(it) }
        }


    }
}

// Pantalla 3 - Reproductor
@Composable
fun PantallaTres(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Reproductor",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Chef Table Radio",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Eli Kulp",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Retroceder */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MediumBlue,
                            contentColor = LightText
                        )
                    ) {
                        Text("⏪")
                    }

                    Button(
                        onClick = { /* Play/Pause */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MediumBlue,
                            contentColor = LightText
                        )
                    ) {
                        Text("▶️")
                    }

                    Button(
                        onClick = { /* Avanzar */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MediumBlue,
                            contentColor = LightText
                        )
                    ) {
                        Text("⏩")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(DarkBlue)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .height(4.dp)
                            .background(MediumBlue)
                    )
                }
            }
        }

    }
}

// Pantalla 4 - Chat
@Composable
fun PantallaCuatro(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Chat",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Jessica Roberts",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = DarkBlue,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Hey! Hope it's...",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        )
                    )
                }
            }
        }

    }
}

// Pantalla 5 - Pasos
@Composable
fun PantallaCinco(onNext: () -> Unit, onPrev: () -> Unit) {
    val steps = 6562
    val goal = 8000

    BaseScreen(
        title = "Pasos",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = steps.toString(),
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightText
                    )
                )

                Text(
                    text = "/$goal",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(12.dp)
                        .background(DarkBlue)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(steps.toFloat() / goal)
                            .height(12.dp)
                            .background(MediumBlue)
                    )
                }
            }
        }

    }
}

// Pantalla 6 - Carrera
@Composable
fun PantallaSeis(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Actividad",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1 carrera esta semana",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "本 方 品",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MediumBlue,
                        contentColor = LightText
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp)
                ) {
                    Text("Más detalles")
                }
            }
        }

    }
}

// Pantalla 7 - Yoga
@Composable
fun PantallaSiete(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Power Yoga",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Última sesión 45m",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MediumBlue,
                        contentColor = LightText
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp)
                ) {
                    Text("Comenzar")
                }
            }
        }

    }
}

// Pantalla 8 - Genérica
@Composable
fun PantallaOcho(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Pantalla 8",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Text(
                text = "Contenido de la pantalla 8",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = LightText
                )
            )
        }

    }
}

/*@Composable
fun PantallaOcho(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Pantalla 8",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            ScalingLazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                item {
                    Text(
                        text = "Selecciona una carátula",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                // Chips para cada carátula
                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula1") */ },
                        label = { Text("Carátula 1") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }

                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula2") */ },
                        label = { Text("Carátula 2") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }
                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula2") */ },
                        label = { Text("Carátula 2") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }
                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula2") */ },
                        label = { Text("Carátula 2") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }
                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula2") */ },
                        label = { Text("Carátula 2") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }
                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula2") */ },
                        label = { Text("Carátula 2") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }
                item {
                    Chip(
                        onClick = { /* navController.navigate("caratula2") */ },
                        label = { Text("Carátula 2") },
                        colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                    )
                }

                // Continúa con los demás chips...
                // (Puedes copiar el resto de los items de chips aquí)


            }
        }
    }
}*/

// Pantalla 9 - Ritmo
@Composable
fun PantallaNueve(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Ritmo",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "7:21 / mi",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Running",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = LightText
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(4.dp)
                        .background(DarkBlue)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .height(4.dp)
                            .background(MediumBlue)
                    )
                }
            }
        }

    }
}

// Pantalla 10 - Resumen
@Composable
fun PantallaDiez(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Resumen",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "¡Buen trabajo!",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Tiempo activo",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "45:23",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MediumBlue
                        )
                    )
                }

                Divider(
                    color = DarkBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Distancia",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "20.6km",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = MediumBlue
                        )
                    )
                }
            }
        }

    }
}

// Pantalla 11 - Velocidad
@Composable
fun PantallaOnce(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Velocidad",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = "Velocidad Máx",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "46.5",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = MediumBlue
                        )
                    )
                    Text(
                        text = "mph",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        ),
                        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
                    )
                }

                Divider(
                    color = DarkBlue,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = "Distancia",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "21.8",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = MediumBlue
                        )
                    )
                    Text(
                        text = "millas",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        ),
                        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
                    )
                }
            }
        }

    }
}

// Pantalla 12 - Equipo
@Composable
fun PantallaDoce(onNext: () -> Unit, onPrev: () -> Unit) {
    BaseScreen(
        title = "Equipo",
        onNext = onNext,
        onPrev = onPrev
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Aplicación:",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        )
                    )

                    Text(
                        text = "Inicia un entrenamiento en tu móvil",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Equipo Peloton:",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LightText
                        )
                    )

                    Text(
                        text = "Conectado",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = MediumBlue,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

    }
}
