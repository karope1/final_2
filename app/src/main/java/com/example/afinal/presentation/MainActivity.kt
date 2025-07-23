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
import com.example.afinal.presentation.pantallas.*
import kotlin.math.sqrt
import androidx.compose.material3.Divider
//import androidx.compose.material.Divider

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument

import com.example.afinal.presentation.codigosExtras.*

import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.PositionIndicator

import com.example.afinal.R


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
    val notas = remember { mutableStateListOf<Nota>() }

    NavHost(
        navController = navController,
        startDestination = Screen.Pantalla1.route
    ) {
        composable(Screen.Pantalla1.route) { PantallaUno {
            (navController.navigate(Screen.Pantalla2.route)) }
        }

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

        composable("lista_notas") {
            PantallaListaNotas(navController = navController, notas = notas)
        }

        composable("crear_nota") {
            PantallaCrearNotaNav(navController = navController) { nuevaNota ->
                notas.add(nuevaNota)
            }
        }

        composable(
            "detalle_nota/{notaEncoded}",
            arguments = listOf(navArgument("notaEncoded") { type = NavType.StringType })
        ) { backStackEntry ->
            val notaEncoded = backStackEntry.arguments?.getString("notaEncoded") ?: ""
            val nota = Nota.fromEncodedString(notaEncoded)

            PantallaDetalleNota(
                nota = notas.find { it.contenido == nota.contenido } ?: nota,
                navController = navController,
                onEliminarNota = {
                    notas.removeIf { it.contenido == nota.contenido }
                    navController.popBackStack()
                }
            )
        }

        composable(
            "editar_nota/{notaEncoded}",
            arguments = listOf(navArgument("notaEncoded") { type = NavType.StringType })
        ) { backStackEntry ->
            val notaEncoded = backStackEntry.arguments?.getString("notaEncoded") ?: ""
            val notaOriginal = Nota.fromEncodedString(notaEncoded)

            PantallaEditarNota(
                notaOriginal = notas.find { it.contenido == notaOriginal.contenido } ?: notaOriginal,
                navController = navController,
                onGuardarCambios = { notaEditada ->
                    val index = notas.indexOfFirst { it.contenido == notaOriginal.contenido }
                    if (index != -1) {
                        notas[index] = notaEditada
                    }
                }
            )
        }
        composable(route = "clima") { WearApp(navController) }


        composable(route = "configuracion") { ConfiguracionScreen(navController) }


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
    object CaratulasMenu : Screen("caratulasMenu")
}

// Colores del tema
val DarkBlue = Color(0x6495ED)
val MediumBlue = Color(0xFF1A3A6E)
val LightText = Color(0xFFE0E0E0)
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
fun CaratulasMenu(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        timeText = { TimeText() }
    ) {
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
                    onClick = { navController.navigate("caratula1") },
                    label = { Text("Carátula 1") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }

            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }

            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            item {
                Chip(
                    onClick = { navController.navigate("caratula2") },
                    label = { Text("Carátula 2") },
                    colors = ChipDefaults.primaryChipColors(backgroundColor = Color.DarkGray)
                )
            }
            // Continúa con los demás chips hasta caratula10...
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