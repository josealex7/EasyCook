package com.easycook.app.screens

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

data class VideoReceta(
    val titulo: String,
    val url: String,
    val descripcion: String
)

@Composable
fun VideoScreen() {
    val videos = remember {
        listOf(
            VideoReceta(
                "Lasaña Boloñesa",
                "https://res.cloudinary.com/dtdopreaf/video/upload/v1777340659/lasagna_sxiuui.mp4",
                "Receta clásica italiana de lasaña boloñesa, con capas de pasta, salsa de carne, " +
                        "bechamel y mucho queso. Perfecta para reuniones familiares."
            ),
            VideoReceta(
                "Pizza Casera",
                "https://res.cloudinary.com/dtdopreaf/video/upload/v1777340657/pizza_cdqpia.mp4",
                "Aprende a preparar pizza casera desde cero: masa fermentada, salsa de tomate " +
                        "natural y los mejores ingredientes. Mejor que la de cualquier pizzería."
            ),
            VideoReceta(
                "Empanadas Fritas o al Horno",
                "https://res.cloudinary.com/dtdopreaf/video/upload/v1777340658/empanada_n1t2ya.mp4",
                "Empanadas tradicionales con relleno jugoso. Aprende dos técnicas en un solo " +
                        "video: fritas para el clásico crujiente o al horno para una versión más ligera."
            )
        )
    }
    var videoActual by remember { mutableStateOf(videos.first()) }
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    // Cuando cambia el video seleccionado, reseteamos los estados
    LaunchedEffect(videoActual.url) {
        isLoading = true
        hasError = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Video Recetas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Reproductor con controles nativos (play, pausa, avance)",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Reproductor real con VideoView nativo (proporción 16:9)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(Color(0xFF1A1A1A)),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        VideoView(context).apply {
                            val controller = MediaController(context)
                            controller.setAnchorView(this)
                            setMediaController(controller)
                            setVideoURI(Uri.parse(videoActual.url))
                            setOnPreparedListener { mp ->
                                mp.isLooping = false
                                isLoading = false
                                start()
                            }
                            setOnErrorListener { _, _, _ ->
                                isLoading = false
                                hasError = true
                                true
                            }
                        }
                    },
                    update = { videoView ->
                        if (videoView.tag != videoActual.url) {
                            videoView.tag = videoActual.url
                            videoView.setVideoURI(Uri.parse(videoActual.url))
                        }
                    }
                )

                // Overlay de carga
                if (isLoading && !hasError) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayCircle,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(56.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        CircularProgressIndicator(
                            color = Color(0xFF4CAF50),
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            "Cargando ${videoActual.titulo}...",
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }

                // Overlay de error
                if (hasError) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ErrorOutline,
                            contentDescription = null,
                            tint = Color(0xFFE57373),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "No se pudo cargar el video",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            "Verifica tu conexión e intenta de nuevo",
                            color = Color.LightGray,
                            fontSize = 11.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            onClick = {
                                isLoading = true
                                hasError = false
                                // Forzar recarga: cambiamos a otro y volvemos
                                val temp = videoActual
                                videoActual = videos.first { it != temp }
                                videoActual = temp
                            }
                        ) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = null,
                                tint = Color(0xFF4CAF50)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Reintentar", color = Color(0xFF4CAF50))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Título y descripción del video
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    videoActual.titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF222222)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    videoActual.descripcion,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Lista de videos disponibles
        Text(
            "Otros videos disponibles",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(8.dp))

        videos.forEach { video ->
            val esActual = video == videoActual
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (esActual) Color(0xFFd4f5d4) else Color(0xFFF5F5F5)
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = { videoActual = video }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (esActual) Icons.Filled.PlayCircle else Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        video.titulo,
                        fontSize = 14.sp,
                        fontWeight = if (esActual) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.weight(1f)
                    )
                    if (esActual) {
                        Text(
                            "Reproduciendo",
                            fontSize = 11.sp,
                            color = Color(0xFF4CAF50)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Detalles
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFd4f5d4))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Sobre el reproductor", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Los videos están alojados en Cloudinary y se reproducen con el componente " +
                            "nativo VideoView de Android. Los controles (play, pausa, barra de " +
                            "progreso, avance/retroceso) aparecen al tocar el video.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
