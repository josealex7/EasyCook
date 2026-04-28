package com.easycook.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class Receta(
    val nombre: String,
    val descripcion: String,
    val tiempo: String,
    val dificultad: String,
    val imagenUrl: String
)

@Composable
fun FotosScreen() {
    val recetas = remember {
        listOf(
            Receta(
                "Pasta Primavera",
                "Una deliciosa pasta con verduras frescas de temporada, aceite de oliva, ajo y hierbas aromáticas como albahaca y orégano. Perfecta para una comida ligera y nutritiva en cualquier ocasión.",
                "25 min", "Fácil",
                "https://res.cloudinary.com/dtdopreaf/image/upload/v1777341099/pastaprimavera_gv5bia.jpg"
            ),
            Receta(
                "Risotto de Hongos",
                "Cremoso risotto italiano con hongos porcini, parmesano reggiano y un toque de trufa negra. Una receta clásica de la cocina del norte de Italia que requiere paciencia y movimientos constantes.",
                "40 min", "Intermedio",
                "https://res.cloudinary.com/dtdopreaf/image/upload/v1777341099/risotto-de-hongos-casero_n5u2h4.jpg"
            ),
            Receta(
                "Buddha Bowl",
                "Bowl nutritivo con quinoa, aguacate, garbanzos tostados, vegetales asados y aderezo de tahini con limón. Ideal para una comida vegana balanceada y llena de proteínas.",
                "15 min", "Fácil",
                "https://res.cloudinary.com/dtdopreaf/image/upload/v1777341099/buddha-bowl-recipe_aegznc.jpg"
            ),
            Receta(
                "Curry de Lentejas",
                "Curry aromático con lentejas rojas, leche de coco, jengibre, cúrcuma y especias indias tradicionales. Acompañar con arroz basmati y pan naan recién horneado.",
                "35 min", "Fácil",
                "https://res.cloudinary.com/dtdopreaf/image/upload/v1777341099/curry-de-lentejas_vquluf.jpg"
            ),
            Receta(
                "Tacos al Pastor",
                "Tacos mexicanos tradicionales con carne marinada en achiote, piña asada, cilantro y cebolla. Servidos en tortillas de maíz hechas a mano con salsa verde picante.",
                "50 min", "Intermedio",
                "https://res.cloudinary.com/dtdopreaf/image/upload/v1777341098/tacos-al-pastor_exgde2.jpg"
            ),
            Receta(
                "Tarta de Manzana",
                "Clásica tarta francesa con manzanas caramelizadas, canela y una base de hojaldre crujiente. Acompañar con una bola de helado de vainilla para un postre irresistible.",
                "60 min", "Intermedio",
                "https://res.cloudinary.com/dtdopreaf/image/upload/v1777341099/tarta-de-manzana_ryvv3a.jpg"
            )
        )
    }

    var seleccionada by remember { mutableStateOf<Receta?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Galería de Recetas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50)
        )
        Text(
            text = "Toca una imagen para ver su descripción",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Detalle de la receta seleccionada
        if (seleccionada != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFd4f5d4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = seleccionada!!.imagenUrl,
                            contentDescription = seleccionada!!.nombre,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFEFEFEF))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                seleccionada!!.nombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Row {
                                Icon(
                                    Icons.Filled.Timer,
                                    contentDescription = null,
                                    tint = Color(0xFF4CAF50),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    seleccionada!!.tiempo,
                                    fontSize = 12.sp,
                                    color = Color(0xFF4CAF50)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Icon(
                                    Icons.Filled.BarChart,
                                    contentDescription = null,
                                    tint = Color(0xFF4CAF50),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    seleccionada!!.dificultad,
                                    fontSize = 12.sp,
                                    color = Color(0xFF4CAF50)
                                )
                            }
                        }
                        IconButton(onClick = { seleccionada = null }) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Cerrar",
                                tint = Color.Gray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        seleccionada!!.descripcion,
                        fontSize = 13.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Lista de imágenes con scroll (LazyColumn)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(recetas) { receta ->
                val isSelected = seleccionada == receta
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            seleccionada = if (isSelected) null else receta
                        }
                        .then(
                            if (isSelected)
                                Modifier.border(
                                    2.dp,
                                    Color(0xFF4CAF50),
                                    RoundedCornerShape(16.dp)
                                )
                            else Modifier
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(if (isSelected) 8.dp else 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Imagen real desde Cloudinary
                        AsyncImage(
                            model = receta.imagenUrl,
                            contentDescription = receta.nombre,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(110.dp)
                                .background(Color(0xFFEFEFEF))
                        )
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                receta.nombre,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row {
                                Icon(
                                    Icons.Filled.Timer,
                                    contentDescription = null,
                                    tint = Color(0xFF4CAF50),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    receta.tiempo,
                                    fontSize = 12.sp,
                                    color = Color(0xFF4CAF50)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                if (isSelected) "Tocada — ver detalle arriba"
                                else "Toca para ver descripción",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

