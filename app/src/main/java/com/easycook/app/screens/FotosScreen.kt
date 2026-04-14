package com.easycook.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FotosScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Recetas Destacadas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(16.dp))

        RecetaCard(
            nombre = "Pasta Primavera",
            descripcion = "Una deliciosa pasta con verduras frescas de temporada, aceite de oliva y hierbas aromáticas.",
            tiempo = "25 min",
            dificultad = "Fácil",
            color = Color(0xFFFFE0B2)
        )

        RecetaCard(
            nombre = "Risotto de Hongos",
            descripcion = "Cremoso risotto italiano con hongos porcini, parmesano y un toque de trufa negra.",
            tiempo = "40 min",
            dificultad = "Intermedio",
            color = Color(0xFFE1BEE7)
        )

        RecetaCard(
            nombre = "Ensalada Buddha Bowl",
            descripcion = "Bowl nutritivo con quinoa, aguacate, garbanzos tostados y aderezo de tahini.",
            tiempo = "15 min",
            dificultad = "Fácil",
            color = Color(0xFFB2DFDB)
        )

        RecetaCard(
            nombre = "Curry de Lentejas",
            descripcion = "Curry aromático con lentejas rojas, leche de coco y especias indias tradicionales.",
            tiempo = "35 min",
            dificultad = "Fácil",
            color = Color(0xFFFFF9C4)
        )
    }
}

@Composable
fun RecetaCard(
    nombre: String,
    descripcion: String,
    tiempo: String,
    dificultad: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            // Imagen simulada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(descripcion, fontSize = 13.sp, color = Color.Gray, lineHeight = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Icon(Icons.Filled.Timer, contentDescription = null,
                        tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(tiempo, fontSize = 12.sp, color = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Filled.BarChart, contentDescription = null,
                        tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(dificultad, fontSize = 12.sp, color = Color(0xFF4CAF50))
                }
            }
        }
    }
}