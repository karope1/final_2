package com.example.afinal.presentation.codigosExtras

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.foundation.lazy.ScalingLazyListScope
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


////////////////////////////////////////////////////////////////////////////////////////////////
// Producto a enseñar
////////////////////////////////////////////////////////////////////////////////////////////////


@Composable
fun PantallaBase(
    title: String,
    onNext: (() -> Unit)? = null,
    onPrev: (() -> Unit)? = null,
    content: ScalingLazyListScope.() -> Unit

) {
    val listState = androidx.wear.compose.foundation.lazy.rememberScalingLazyListState()
    Scaffold(
        timeText = { TimeText() },
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        androidx.wear.compose.foundation.lazy.ScalingLazyColumn(
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
                        color = Color.Cyan
                    )
                )
            }
            // Muestra el contenido
            content()

        }
    }
}

/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

//Correcto funcionamiento al crear las notas
data class Nota(
    val contenido: String,
    val fecha: LocalDateTime = LocalDateTime.now()
) {
    // Función para codificar la nota para navegación
    fun toEncodedString(): String = Uri.encode("${contenido}|${fecha}")

    companion object {
        // Función para decodificar desde string de navegación
        fun fromEncodedString(encoded: String): Nota {
            val decoded = Uri.decode(encoded)
            val parts = decoded.split("|")
            return Nota(
                contenido = parts[0],
                fecha = if (parts.size > 1) LocalDateTime.parse(parts[1]) else LocalDateTime.now()
            )
        }
    }
}


//Gestiona la transición (lógica) entre pantalas al guardar la nota
//permitiendo mostrarse la nota al estar en el HUB
@Composable
fun PantallaCrearNotaNav(
    navController: NavHostController,
    onGuardarNota: (Nota) -> Unit
) {
    var texto by remember { mutableStateOf("") }
    val fechaActual = remember {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }

    PantallaCrearNota(
        notaTexto = texto,
        onNotaChange = { texto = it },
        fechaActual = fechaActual,
        onGuardar = {
            if (texto.isNotBlank()) {
                onGuardarNota(Nota(contenido = texto))
                navController.popBackStack()
            }
        }
    )
}

/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

//Crea la nota con fecha
@Composable
fun PantallaCrearNota(
    notaTexto: String,
    onNotaChange: (String) -> Unit,
    onGuardar: () -> Unit,
    fechaActual: String
) {
    PantallaBase(
        title = "Crear Nota",
        content = {
            item {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fecha: $fechaActual",
                        style = MaterialTheme.typography.title3,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = notaTexto,
                        onValueChange = onNotaChange,
                        label = { Text("Escribe tu nota") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        singleLine = false
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onGuardar,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Guardar Nota")
                    }
                }
            }
        }
    )
}


//HUB para crear y visualizar las notas
@Composable
fun PantallaListaNotas(
    navController: NavHostController,
    notas: SnapshotStateList<Nota>
) {
    PantallaBase(
        title = "Mis Notas",
        content = {
            item {
                Button(
                    onClick = { navController.navigate("crear_nota") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("➕ Nueva Nota")
                }
            }

            items(notas) { nota ->
                Button(
                    onClick = {
                        navController.navigate("detalle_nota/${nota.toEncodedString()}")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            nota.contenido.take(30) + if (nota.contenido.length > 30) "..." else "",
                            style = MaterialTheme.typography.title3
                        )
                        Text(
                            nota.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")),
                            style = MaterialTheme.typography.title3,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Button(
                    onClick = { navController.navigate("aplicaciones") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("Regresar")
                }
            }
        }
    )
}

//Permite la edición de las notas que se elijan
@Composable
fun PantallaEditarNota(
    notaOriginal: Nota,
    navController: NavHostController,
    onGuardarCambios: (Nota) -> Unit
) {
    var textoEditado by remember { mutableStateOf(notaOriginal.contenido) }
    val fechaActual = notaOriginal.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

    PantallaBase(
        title = "Editar Nota",
        content = {
            item {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fecha: $fechaActual",
                        style = MaterialTheme.typography.title3,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = textoEditado,
                        onValueChange = { textoEditado = it },
                        label = { Text("Edita tu nota") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        singleLine = false
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val notaActualizada = notaOriginal.copy(
                                contenido = textoEditado,
                                fecha = notaOriginal.fecha
                            )
                            onGuardarCambios(notaActualizada)
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("💾 Guardar Cambios")
                    }
                }
            }
        }
    )
}


//Permite la correcta visualización de la nota seleccionada
@Composable
fun PantallaDetalleNota(
    nota: Nota,
    navController: NavHostController,
    onEliminarNota: () -> Unit
) {
    PantallaBase(
        title = "Detalle Nota",
        content = {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Fecha: ${nota.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}",
                        style = MaterialTheme.typography.title3,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = nota.contenido,
                        style = MaterialTheme.typography.title3
                    )
                }
            }


            item {
                Button(
                    onClick = { navController.navigate("editar_nota/${nota.toEncodedString()}") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("✏️ Editar Nota")
                }
            }

            item {
                Button(
                    onClick = onEliminarNota,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),

                    ) {
                    Text("🗑️ Eliminar Nota")
                }
            }
        }
    )
}