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
                Triple(Icons.Filled.Star, "Nivel", "Intermedio")
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Estudios
        TextCard(
            title = "Estudios",
            icon = Icons.Filled.School,
            content = "• Técnico en Gastronomía — Instituto Culinario Nacional (2018-2020)\n" +
                    "• Diplomado en Cocina Italiana — Le Cordon Bleu Online (2021)\n" +
                    "• Curso de Pastelería Profesional — Academia Dulce Arte (2022)\n" +
                    "• Certificación en Manipulación de Alimentos — INVIMA (2023)\n" +
                    "• Taller de Cocina Vegetariana Avanzada — Chef School (2024)"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Experiencia
        TextCard(
            title = "Experiencia",
            icon = Icons.Filled.Work,
            content = "• Chef Ejecutivo — Restaurante La Trattoria (2023 - Actualidad)\n" +
                    "Encargado de la cocina principal, diseño de menús estacionales y " +
                    "coordinación de un equipo de 8 personas.\n\n" +
                    "• Sous Chef — Hotel Boutique El Mirador (2021 - 2023)\n" +
                    "Apoyo al chef principal, preparación de platos para eventos y " +
                    "supervisión del área de pastelería.\n\n" +
                    "• Cocinero de línea — Bistró Gourmet (2020 - 2021)\n" +
                    "Preparación de platos a la carta, manejo de estaciones frías y calientes."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Biografía
        TextCard(
            title = "Sobre mí",
            icon = Icons.Filled.Info,
            content = "Soy un cocinero apasionado por los sabores auténticos y las recetas tradicionales " +
                    "con un toque moderno. Me especializo en cocina mediterránea y vegetariana. " +
                    "Mi filosofía es simple: ingredientes frescos, técnica precisa y mucho amor por lo " +
                    "que hago. Disfruto enseñar y compartir conocimiento con otros amantes de la cocina, " +
                    "y siempre estoy buscando nuevas inspiraciones para crear experiencias gastronómicas " +
                    "memorables. Cuando no estoy en la cocina, suelo recorrer mercados locales en " +
                    "busca de ingredientes únicos o probando recetas en familia."
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

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TextCard(title: String, icon: ImageVector, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFd4f5d4))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.width(8.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Caja con scroll interno para textos largos (cumple requisito)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 140.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = content,
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )
            }
        }
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