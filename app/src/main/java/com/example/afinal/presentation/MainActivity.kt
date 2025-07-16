/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.afinal.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.*
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.afinal.presentation.theme.FinalTheme
import kotlin.math.sqrt
import androidx.compose.material3.Divider
//import androidx.compose.material.Divider


import androidx.compose.runtime.Composable

import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.PositionIndicator






class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalTheme {
                WearApp()
            }
        }
    }
}

@Composable
fun WearApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Pantalla1.route
    ) {
        composable(Screen.Pantalla1.route) { PantallaUno { navController.navigate(Screen.Pantalla2.route) } }
        composable(Screen.Pantalla2.route) { PantallaDos(
            onNext = { navController.navigate(Screen.Pantalla3.route) },
            onPrev = { navController.navigate(Screen.Pantalla1.route) }
        ) }
        composable(Screen.Pantalla3.route) { PantallaTres(
            onNext = { navController.navigate(Screen.Pantalla4.route) },
            onPrev = { navController.navigate(Screen.Pantalla2.route) }
        ) }
        composable(Screen.Pantalla4.route) { PantallaCuatro(
            onNext = { navController.navigate(Screen.Pantalla5.route) },
            onPrev = { navController.navigate(Screen.Pantalla3.route) }
        ) }
        composable(Screen.Pantalla5.route) { PantallaCinco(
            onNext = { navController.navigate(Screen.Pantalla6.route) },
            onPrev = { navController.navigate(Screen.Pantalla4.route) }
        ) }
        composable(Screen.Pantalla6.route) { PantallaSeis(
            onNext = { navController.navigate(Screen.Pantalla7.route) },
            onPrev = { navController.navigate(Screen.Pantalla5.route) }
        ) }
        composable(Screen.Pantalla7.route) { PantallaSiete(
            onNext = { navController.navigate(Screen.Pantalla8.route) },
            onPrev = { navController.navigate(Screen.Pantalla6.route) }
        ) }
        composable(Screen.Pantalla8.route) { PantallaOcho(
            onNext = { navController.navigate(Screen.Pantalla9.route) },
            onPrev = { navController.navigate(Screen.Pantalla7.route) }
        ) }
        composable(Screen.Pantalla9.route) { PantallaNueve(
            onNext = { navController.navigate(Screen.Pantalla10.route) },
            onPrev = { navController.navigate(Screen.Pantalla8.route) }
        ) }
        composable(Screen.Pantalla10.route) { PantallaDiez(
            onNext = { navController.navigate(Screen.Pantalla11.route) },
            onPrev = { navController.navigate(Screen.Pantalla9.route) }
        ) }
        composable(Screen.Pantalla11.route) { PantallaOnce(
            onNext = { navController.navigate(Screen.Pantalla12.route) },
            onPrev = { navController.navigate(Screen.Pantalla10.route) }
        ) }
        composable(Screen.Pantalla12.route) { PantallaDoce(
            onNext = { navController.navigate(Screen.Pantalla13.route) },
            onPrev = { navController.navigate(Screen.Pantalla11.route) }
        ) }
        composable(Screen.Pantalla13.route) { PantallaTrece(
            onNext = { navController.navigate(Screen.Pantalla14.route) },
            onPrev = { navController.navigate(Screen.Pantalla12.route) }
        ) }
        composable(Screen.Pantalla14.route) { PantallaCatorce(
            onPrev = { navController.navigate(Screen.Pantalla13.route) }
        ) }
    }
}

sealed class Screen(val route: String) {
    object Pantalla1 : Screen("pantalla1")
    object Pantalla2 : Screen("pantalla2")
    object Pantalla3 : Screen("pantalla3")
    object Pantalla4 : Screen("pantalla4")
    object Pantalla5 : Screen("pantalla5")
    object Pantalla6 : Screen("pantalla6")
    object Pantalla7 : Screen("pantalla7")
    object Pantalla8 : Screen("pantalla8")
    object Pantalla9 : Screen("pantalla9")
    object Pantalla10 : Screen("pantalla10")
    object Pantalla11 : Screen("pantalla11")
    object Pantalla12 : Screen("pantalla12")
    object Pantalla13 : Screen("pantalla13")
    object Pantalla14 : Screen("pantalla14")
}

// Colores del tema
private val DarkBlue = Color(0x6495ED)
private val MediumBlue = Color(0xFF1A3A6E)
private val LightText = Color(0xFFE0E0E0)
private val DarkBackground = Color(0xFF121212)

// Componentes reutilizables
@Composable
fun BaseScreen(
    title: String,
    onNext: (() -> Unit)? = null,
    onPrev: (() -> Unit)? = null,
    content: ScalingLazyListScope.() -> Unit

) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        timeText = {TimeText()},
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediumBlue
                    )
                )
            }
            // Llama al contenido
            content()

            item {
                NavigationButtons(onPrev = onPrev, onNext = onNext)
            }
        }
    }
}


@Composable
fun NavigationButtons(
    onPrev: (() -> Unit)?,
    onNext: (() -> Unit)?,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        onPrev?.let {
            Button(
                onClick = it,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MediumBlue,
                    contentColor = LightText
                ),
                modifier = Modifier.width(100.dp)
            ) {
                Text("Anterior")
            }
        }

        onNext?.let {
            Button(
                onClick = it,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MediumBlue,
                    contentColor = LightText
                ),
                modifier = Modifier.width(100.dp)
            ) {
                Text("Siguiente")
            }
        }
    }
}

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

data class CalculatorOperation(
    var firstOperand: Double = 0.0,
    var operator: String = "",
    var secondOperand: Double = 0.0
)

@Composable
fun CalculatorButtonRow(
    buttons: List<String>,
    onButtonClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        buttons.forEach { button ->
            CalculatorButton(
                text = button,
                onClick = { onButtonClick(button) },
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.size(40.dp)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkBlue,
            contentColor = LightText
        ),
        modifier = modifier
    ) {
        Text(text)
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

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FinalTheme {
        PantallaUno {}
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewCalculadora() {
    FinalTheme {
        PantallaDos({}, {})
    }
}