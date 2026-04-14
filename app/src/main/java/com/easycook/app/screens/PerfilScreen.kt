package com.easycook.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Avatar circular
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFF4CAF50), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Chef Alexander", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Cocinero apasionado", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(20.dp))

        // Estadísticas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatCard("42", "Recetas")
            StatCard("128", "Seguidores")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Info personal
        InfoCard(
            title = "Información Personal",
            items = listOf(
                Triple(Icons.Filled.Favorite, "Preferencias", "Vegetariano"),
                Triple(Icons.Filled.Warning, "Alergias", "Ninguna"),
                Triple(Icons.Filled.Star, "Experiencia", "Intermedia")
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ajustes
        InfoCard(
            title = "Ajustes de Cuenta",
            items = listOf(
                Triple(Icons.Filled.Notifications, "Notificaciones", ""),
                Triple(Icons.Filled.Lock, "Privacidad y seguridad", ""),
                Triple(Icons.Filled.ExitToApp, "Cerrar sesión", "")
            )
        )
    }
}

@Composable
fun StatCard(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun InfoCard(title: String, items: List<Triple<ImageVector, String, String>>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFd4f5d4))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { (icon, label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(icon, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(label, fontSize = 14.sp, modifier = Modifier.weight(1f))
                    if (value.isNotEmpty()) Text(value, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}