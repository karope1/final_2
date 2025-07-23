package com.example.afinal.presentation.pantallas

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.SwipeToDismissBoxState
import androidx.wear.compose.material.SwipeToDismissValue
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.itemsIndexed
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.afinal.R

// Mueve Song fuera de los Composables si aún no lo has hecho
data class Song(val resourceId: Int, val title: String, val artist: String)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

// Tus colores personalizados
val MyPurple = Color(0xFF6200EE) // Un morado estándar de Material, ajústalo si tienes otro
val PureBlack = Color.Black
val PureWhite = Color.White

@Composable
fun WearApp() {
    val wearColors = Colors(
        primary = MyPurple,             // Color principal para elementos interactivos (botones)
        primaryVariant = Color(0xFF3700B3), // Una variante más oscura del morado
        secondary = MyPurple,           // Puedes usar el mismo morado o un color de acento
        secondaryVariant = Color(0xFF3700B3),
        background = PureBlack,         // Fondo de la aplicación
        surface = Color(0xFF121212),    // Color para superficies sobre el fondo (como tarjetas, si las usaras) - un gris muy oscuro casi negro
        error = Color(0xFFCF6679),
        onPrimary = PureWhite,          // Color del contenido (texto/iconos) SOBRE el color primario
        onSecondary = PureWhite,        // Color del contenido SOBRE el color secundario
        onBackground = PureWhite,       // Color del contenido SOBRE el fondo
        onSurface = PureWhite,          // Color del contenido SOBRE las superficies
        onError = PureBlack
    )


    MaterialTheme(
        colors = wearColors
        // shapes = Shapes, // Si tienes shapes personalizados
    ) {
        //AppRoot()
    }
}

enum class AppScreen {
    Menu,
    MusicPlayer,
    Playlist,
}


@Composable
fun AppRoot(navController: NavHostController) {
    var currentScreen by remember { mutableStateOf(AppScreen.Menu) }
    var currentSongIndexForPlayer: Int? by remember { mutableStateOf(null) }
    var currentPlaylistPlayingIndex by remember { mutableStateOf(0) }

    val swipeToDismissState = rememberSwipeToDismissBoxState()

    // CORRECCIÓN: Lista de canciones ahora usa TrackInfo y se llama trackList
    val trackList = remember {
        listOf(
            TrackInfo(
                id = "track001", // Añade un ID único para cada pista
                resourceId = R.raw.cancion1,
                title = "My eyes",
                artist = "Travis Scott",
                coverArtResourceId = R.drawable.ic_cover_art_placeholder // REEMPLAZA con tu drawable real o usa DefaultCoverArt.placeholder
            ),
            TrackInfo(
                id = "track002",
                resourceId = R.raw.cancion2,
                title = "Culpable o no",
                artist = "Luis Miguel",
                coverArtResourceId = R.drawable.ic_cover_art_placeholder2 // REEMPLAZA
            ),
            TrackInfo(
                id = "track001", // Añade un ID único para cada pista
                resourceId = R.raw.cancion3,
                title = "Se fue la luz",
                artist = "LATIN MAFIA, Jeese Baez",
                coverArtResourceId = R.drawable.ic_cover_art_placeholder3 // REEMPLAZA con tu drawable real o usa DefaultCoverArt.placeholder
            ),
            TrackInfo(
                id = "track001", // Añade un ID único para cada pista
                resourceId = R.raw.cancion4,
                title = "A lot",
                artist = "21 Savage ft. J. Cole",
                coverArtResourceId = R.drawable.ic_cover_art_placeholder4 // REEMPLAZA con tu drawable real o usa DefaultCoverArt.placeholder
            ),
            TrackInfo(
                id = "track001", // Añade un ID único para cada pista
                resourceId = R.raw.cancion5,
                title = "There Is a Light That Never Goes Out (2011 Remaster)",
                artist = "The Smiths",
                coverArtResourceId = R.drawable.ic_cover_art_placeholder5 // REEMPLAZA con tu drawable real o usa DefaultCoverArt.placeholder
            )
        )
    }

    LaunchedEffect(swipeToDismissState.currentValue, currentScreen) {
        if (swipeToDismissState.targetValue == SwipeToDismissValue.Dismissed) {
            if (currentScreen != AppScreen.Menu) {
                currentScreen = AppScreen.Menu
                swipeToDismissState.snapTo(SwipeToDismissValue.Default) // Restablece el estado
            } else {
                // Si ya estamos en el menú y se intenta descartar, simplemente lo restablecemos.
                swipeToDismissState.snapTo(SwipeToDismissValue.Default)
            }
        }
    }

    if (currentScreen == AppScreen.Menu) {
        MainMenuScreen { selectedAppScreen ->
            currentScreen = selectedAppScreen
            if (selectedAppScreen == AppScreen.MusicPlayer && trackList.isNotEmpty()) { // Usa trackList
                currentSongIndexForPlayer = currentSongIndexForPlayer ?: 0
            }
        }
    } else {
        SwipeToDismissScreenWrapper(
            swipeToDismissState = swipeToDismissState,
        ) {
            when (currentScreen) {
                AppScreen.MusicPlayer -> MusicPlayerScreen(
                    initialSongIndex = currentSongIndexForPlayer,
                    songList = trackList, // <--- CORREGIDO: Pasa trackList (List<TrackInfo>)
                    onNavigateToPlaylist = { index, currentTracks -> // currentTracks es List<TrackInfo>
                        currentPlaylistPlayingIndex = index
                        currentSongIndexForPlayer = index
                        currentScreen = AppScreen.Playlist
                    },
                    onBack = { currentScreen = AppScreen.Menu },
                    navController = {navController.navigate("aplicaciones")}
                )

                AppScreen.Playlist -> PlaylistScreen( // Asume que PlaylistScreen también espera List<TrackInfo>
                    songList = trackList, // <--- CORREGIDO: Pasa trackList
                    currentPlayingIndex = currentPlaylistPlayingIndex,
                    onSongSelected = { selectedIndex ->
                        currentSongIndexForPlayer = selectedIndex
                        currentPlaylistPlayingIndex = selectedIndex
                        currentScreen = AppScreen.MusicPlayer
                    },
                    onBack = { currentScreen = AppScreen.MusicPlayer }
                )
                else -> {
                    Text("Pantalla Desconocida - Volviendo al menú")
                    LaunchedEffect(Unit) { currentScreen = AppScreen.Menu }
                }
            }
        }
    }
}



// Wrapper para androidx.wear.compose.material.SwipeToDismissBox
@Composable
fun SwipeToDismissScreenWrapper(
    swipeToDismissState: SwipeToDismissBoxState, // De androidx.wear.compose.material
    screenContent: @Composable () -> Unit // Renombrado para claridad, es el contenido principal
) {
    SwipeToDismissBox(
        state = swipeToDismissState,
        hasBackground = true, // Indica que quieres dibujar un fondo
        backgroundKey = swipeToDismissState.currentValue, // Puedes usar el valor actual o un ID constante
        contentKey = swipeToDismissState.targetValue,     // Puedes usar el valor objetivo o un ID constante
        // El contenido del fondo y el principal se definen en esta lambda
        content = { isBackground -> // Este es el BoxScope.(isBackground: Boolean) -> Unit
            if (isBackground) {
                // DIBUJA TU FONDO AQUÍ
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        // Usar colores del tema de Wear Material si ese es tu tema principal
                        .background(MaterialTheme.colors.background.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colors.onBackground, // Usar colores del tema de Wear
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                // DIBUJA TU CONTENIDO PRINCIPAL AQUÍ
                screenContent()
            }
        }
    )
}




@Composable
fun MainMenuScreen(onAppSelected: (AppScreen) -> Unit) {
    var currentTime by remember { mutableStateOf("") }
    var currentDate by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("24°C") }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
            currentDate = SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(Date())
            delay(1000)
        }
    }

    val apps = listOf(
        AppItem("Música", AppScreen.MusicPlayer, R.drawable.ic_music)
    )

    Column( // Column principal de la pantalla
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        // Grid de aplicaciones con footer incluido
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f) // Para que ocupe el espacio restante
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp) // Padding para que el último ítem no toque el footer
        ) {
            items(apps) { app ->
                AppMenuItem(
                    name = app.name,
                    iconRes = app.iconRes,
                    onClick = { onAppSelected(app.screen) }
                )
            }


        }
    }
}



data class AppItem(val name: String, val screen: AppScreen, val iconRes: Int)

@Composable
fun AppMenuItem(name: String, iconRes: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            //.padding(4.dp)
            //.fillMaxWidth()
            //.aspectRatio(1f)
            .size(48.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = name,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicPlayerScreen(
    navController: () -> Unit,
    initialSongIndex: Int? = null,
    songList: List<TrackInfo>, // <--- CAMBIO: Ahora es List<TrackInfo>
    onNavigateToPlaylist: (currentIndex: Int, songList: List<TrackInfo>) -> Unit, // <--- CAMBIO
    onBack: () -> Unit
) {
    if (songList.isEmpty()) {
        Scaffold(timeText = { TimeText() }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay canciones disponibles.")
            }
        }
        return
    }

    val context = LocalContext.current
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    var currentIndex by remember(songList.size) {
        mutableStateOf(initialSongIndex?.takeIf { it >= 0 && it < songList.size } ?: 0)
    }
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    val currentVolume = remember { mutableStateOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) }
    val maxVolume = remember { audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) }

    val wearTypography = MaterialTheme.typography
    val wearColors = MaterialTheme.colors

    // currentTrack (renombrado de currentSong) ahora es un objeto TrackInfo completo
    val currentTrack = remember(currentIndex, songList) {
        songList.getOrElse(currentIndex) { songList.first() }
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(initialSongIndex, songList.size) {
        initialSongIndex?.let { newIndex ->
            if (newIndex >= 0 && newIndex < songList.size && newIndex != currentIndex) {
                currentIndex = newIndex
                isPlaying = true
            }
        }
    }

    // Efecto para el MediaPlayer usa currentTrack.resourceId
    LaunchedEffect(currentTrack.resourceId, currentIndex) {
        Log.d("MusicPlayerScreen", "LaunchedEffect for MediaPlayer. Track: ${currentTrack.title}, Index: $currentIndex, isPlaying: $isPlaying")
        mediaPlayer?.release()
        mediaPlayer = null

        if (currentTrack.resourceId != 0) { // Accede a resourceId desde el objeto currentTrack
            mediaPlayer = MediaPlayer.create(context, currentTrack.resourceId)?.apply {
                setOnErrorListener { mp, what, extra ->
                    Log.e("MusicPlayerScreen", "MediaPlayer Error: what $what, extra $extra. Track: ${currentTrack.title}")
                    isPlaying = false
                    true
                }
                setOnCompletionListener {
                    Log.d("MusicPlayerScreen", "MediaPlayer OnCompletionListener: ${currentTrack.title}")
                    if (currentIndex < songList.size - 1) {
                        currentIndex++
                    } else {
                        isPlaying = false
                    }
                }
            }

            if (mediaPlayer == null) {
                Log.e("MusicPlayerScreen", "MediaPlayer.create devolvió null para resourceId: ${currentTrack.resourceId}")
                isPlaying = false
            } else if (isPlaying) {
                try {
                    startAndLog(mediaPlayer, currentTrack.title)
                } catch (e: IllegalStateException) {
                    Log.e("MusicPlayerScreen", "MediaPlayer start error in LaunchedEffect: ${e.message}", e)
                    isPlaying = false
                }
            }
        } else {
            Log.w("MusicPlayerScreen", "Pista actual con resourceId 0 (inválido): ${currentTrack.title}")
            isPlaying = false
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.d("MusicPlayerScreen", "DisposableEffect: Releasing MediaPlayer")
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Scaffold(
        timeText = { TimeText(modifier = Modifier.padding(top = 6.dp)) },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scrollState = scrollState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(wearColors.background)
                .verticalScroll(scrollState)
                .padding(horizontal = 8.dp, vertical = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // MODIFICACIÓN: Spacer para bajar la carátula
            Spacer(modifier = Modifier.height(28.dp)) // Ajusta esta altura según veas conveniente

            // Carátula Reducida - AHORA USA currentTrack.coverArtResourceId
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(90.dp)
                    .aspectRatio(1f)
                    .background(wearColors.surface, CircleShape) // Fondo por si la imagen es transparente o no carga
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image( // Cambiado de Icon a Image para mostrar la portada correctamente
                    painter = painterResource(id = currentTrack.coverArtResourceId),
                    contentDescription = "Carátula de ${currentTrack.title}",
                    modifier = Modifier.fillMaxSize(), // La imagen llenará el Box circular
                    contentScale = ContentScale.Crop // Asegura que la imagen llene el espacio, recortando si es necesario
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Info Pista Reducida - AHORA USA currentTrack.title y currentTrack.artist
            Text(
                text = currentTrack.title,
                style = wearTypography.title3,
                color = wearColors.onBackground,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )
            Text(
                text = currentTrack.artist,
                style = wearTypography.body2,
                color = wearColors.onBackground.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            IconButton(
                onClick = { onNavigateToPlaylist(currentIndex, songList) }, // songList es List<TrackInfo>
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_playlist_play),
                    contentDescription = "Playlist",
                    modifier = Modifier.fillMaxSize(0.8f),
                    tint = wearColors.onSurface
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (songList.isNotEmpty()) {
                            val currentlyPlaying = isPlaying
                            currentIndex = (currentIndex - 1).mod(songList.size)
                            if (currentlyPlaying) isPlaying = true
                        }
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_skip_previous),
                        contentDescription = "Anterior",
                        modifier = Modifier.fillMaxSize(0.7f),
                        tint = wearColors.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        if (currentTrack.resourceId == 0) { // Usa currentTrack
                            Log.w("MusicPlayerScreen", "Play/Pause: No valid track resource.")
                            return@IconButton
                        }
                        if (mediaPlayer != null) {
                            if (isPlaying) {
                                mediaPlayer?.pause()
                                Log.d("MusicPlayerScreen", "MediaPlayer paused by button")
                            } else {
                                try {
                                    startAndLog(mediaPlayer, currentTrack.title, "button") // Usa currentTrack.title
                                } catch (e: IllegalStateException) {
                                    Log.e("MusicPlayerScreen", "MediaPlayer start error on button: ${e.message}", e)
                                }
                            }
                        }
                        isPlaying = !isPlaying
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .background(wearColors.primary, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                        contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                        modifier = Modifier.fillMaxSize(0.7f),
                        tint = wearColors.onPrimary
                    )
                }

                IconButton(
                    onClick = {
                        if (songList.isNotEmpty()) {
                            val currentlyPlaying = isPlaying
                            currentIndex = (currentIndex + 1) % songList.size
                            if (currentlyPlaying) isPlaying = true
                        }
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_skip_next),
                        contentDescription = "Siguiente",
                        modifier = Modifier.fillMaxSize(0.7f),
                        tint = wearColors.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Volumen",
                style = wearTypography.caption1,
                color = wearColors.onSurface.copy(alpha = 0.7f)
            )
            Slider(
                value = currentVolume.value.toFloat(),
                onValueChange = { newVolume ->
                    val newVolInt = newVolume.toInt()
                    // No es necesario el if (newVolInt != currentVolume.value) aquí
                    // porque onValueChange se llama continuamente mientras se desliza.
                    // El estado se actualiza, y luego la UI refleja el estado.
                    currentVolume.value = newVolInt
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolInt, 0)
                    Log.d("MusicPlayerScreen", "Volume (Slider) changed to: $newVolInt")
                },
                valueRange = 0f..maxVolume.toFloat(),
                steps = if (maxVolume > 0) maxVolume - 1 else 0,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp) // Ajusta el padding según necesites
                    .height(36.dp),
                colors = SliderDefaults.colors( // Opcional: Personaliza los colores para que coincidan con tu tema Wear
                    thumbColor = MaterialTheme.colors.primary,
                    activeTrackColor = MaterialTheme.colors.primary,
                    inactiveTrackColor = MaterialTheme.colors.onSurface.copy(alpha = 0.24f)
                    // Puedes añadir activeTickColor, inactiveTickColor si usas steps visibles
                )
            )

            if (songList.size > 1) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "A continuación:",
                    style = wearTypography.caption2,
                    fontWeight = FontWeight.Bold,
                    color = wearColors.onBackground,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                val nextIndex = (currentIndex + 1) % songList.size
                val nextTrack = songList[nextIndex] // nextTrack es un objeto TrackInfo
                Text(
                    text = "${nextTrack.title} - ${nextTrack.artist}", // Usa info de nextTrack
                    style = wearTypography.caption2.copy(fontSize = 11.sp),
                    color = wearColors.onBackground.copy(alpha = 0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 1.dp)
                )
            }
        }
    }
}

private fun startAndLog(mediaPlayer: MediaPlayer?, trackTitle: String, from: String = "LaunchedEffect") { // Renombré songTitle a trackTitle por consistencia
    try {
        mediaPlayer?.start()
        Log.d("MusicPlayerScreen", "MediaPlayer started for: $trackTitle (from $from)")
    } catch (e: IllegalStateException) {
        Log.e("MusicPlayerScreen", "MediaPlayer start error in startAndLog for $trackTitle: ${e.message}", e)
        throw e
    }
}

// Renombrada de Song a TrackInfo
data class TrackInfo(
    val id: String,
    @RawRes val resourceId: Int,
    val title: String,
    val artist: String,
    @DrawableRes val coverArtResourceId: Int
)




@Composable
fun PlaylistScreen(
    songList: List<TrackInfo>,
    currentPlayingIndex: Int,
    onSongSelected: (Int) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        timeText = { TimeText() },
        positionIndicator = { PositionIndicator(scalingLazyListState = rememberScalingLazyListState()) } // Opcional
    ) {
        val listState = rememberScalingLazyListState()
        // Acceder explícitamente a la tipografía y colores del tema de Wear
        val wearTypography = MaterialTheme.typography // Asumiendo que MaterialTheme aquí SÍ es el de Wear
        val wearColors = MaterialTheme.colors
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(wearColors.background) // Fondo de la pantalla
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centrar el contenido de la columna
        ) {
            item {
                val currentTypography = MaterialTheme.typography // <--- Añade esta línea
                // Ahora intenta usar 'currentTypography' con un estilo que debería existir
                // Por ejemplo, intenta con displayLarge que es estándar en Wear Material
                Text(
                    "Playlist",
                    style = currentTypography.title1,
                    textAlign = TextAlign.Center,
                    color = wearColors.onBackground, // Texto sobre el fondo
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
            itemsIndexed(songList) { index, song ->
                Chip(
                    onClick = { onSongSelected(index) },
                    label = { androidx.compose.material3.Text(song.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                    secondaryLabel = { androidx.compose.material3.Text(song.artist, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                    icon = { // Opcional: pequeño icono de música
                        Icon(
                            painterResource(id = R.drawable.ic_music_note_small), // Necesitarás un icono pequeño
                            contentDescription = null,
                            modifier = Modifier.size(ChipDefaults.IconSize)
                        )
                    },
                    colors = ChipDefaults.chipColors( // Colores del Chip
                        backgroundColor = if (index == currentPlayingIndex) wearColors.primary else wearColors.surface,
                        contentColor = if (index == currentPlayingIndex) wearColors.onPrimary else wearColors.onSurface,
                        secondaryContentColor = (if (index == currentPlayingIndex) wearColors.onPrimary else wearColors.onSurface).copy(alpha = 0.7f),
                        iconColor = if (index == currentPlayingIndex) wearColors.onPrimary else wearColors.onSurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp) // Espaciado entre chips
                )
            }
            item {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = wearColors.primary,
                        contentColor = wearColors.onPrimary
                    )
                ) {
                    Text("Volver al Reproductor")
                }
            }
        }
    }
}

