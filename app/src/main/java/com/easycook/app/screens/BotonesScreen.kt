package com.easycook.app.screens

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotonesScreen() {
    var favorito by remember { mutableStateOf(false) }
    var modoCocinando by remember { mutableStateOf(false) }
    var compartido by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Acciones",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón favoritos
        Button(
            onClick = { favorito = !favorito },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (favorito) Color(0xFFE53935) else Color(0xFF4CAF50)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (favorito) "¡Guardado en favoritos!" else "Añadir a favoritos")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón compartir
        Button(
            onClick = { compartido = !compartido },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (compartido) Color(0xFF1976D2) else Color(0xFF4CAF50)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Share, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (compartido) "¡Receta compartida!" else "Compartir receta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chef's Tip
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFd4f5d4))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.TipsAndUpdates, contentDescription = null,
                        tint = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Chef's Tip", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                        color = Color(0xFF4CAF50))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Organiza tus recetas usando etiquetas como #vegetariano, #rápido o #postre " +
                            "para encontrarlas fácilmente cuando más las necesites.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Modo cocina
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (modoCocinando) Color(0xFF4CAF50) else Color(0xFFF5F5F5)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = null,
                    tint = if (modoCocinando) Color.White else Color(0xFF4CAF50),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    if (modoCocinando) "¡Modo Cocina Activo!" else "Modo Cocina",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (modoCocinando) Color.White else Color.Black
                )
                Text(
                    if (modoCocinando) "Pantalla optimizada para cocinar"
                    else "Activa una vista optimizada mientras cocinas",
                    fontSize = 12.sp,
                    color = if (modoCocinando) Color.White else Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { modoCocinando = !modoCocinando },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (modoCocinando) Color.White else Color(0xFF4CAF50)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        if (modoCocinando) "Desactivar" else "Activar Modo Cocina",
                        color = if (modoCocinando) Color(0xFF4CAF50) else Color.White
                    )
                }
            }
        }
    }
}