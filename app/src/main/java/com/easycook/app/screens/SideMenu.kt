package com.easycook.app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.foundation.background

@Composable
fun SideMenu(selected: String, onSelect: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "easy\nCook",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Íconos de navegación
        MenuIcon(Icons.Filled.Home,     "inicio",  selected, onSelect)
        MenuIcon(Icons.Filled.Person,   "perfil",  selected, onSelect)
        MenuIcon(Icons.Filled.Photo,    "fotos",   selected, onSelect)
        MenuIcon(Icons.Filled.PlayCircle, "video", selected, onSelect)
        MenuIcon(Icons.Filled.Language, "web",     selected, onSelect)
        MenuIcon(Icons.Filled.TouchApp, "botones", selected, onSelect)
    }
}

@Composable
fun MenuIcon(
    icon: ImageVector,
    screen: String,
    selected: String,
    onSelect: (String) -> Unit
) {
    val isSelected = selected == screen
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(48.dp)
            .background(
                color = if (isSelected) Color(0xFF388E3C) else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelect(screen) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = screen,
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}