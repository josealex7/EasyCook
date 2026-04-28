package com.easycook.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SideMenu(selected: String, onSelect: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "easyCook",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        MenuItem(Icons.Filled.Home, "Inicio", "inicio", selected, onSelect)
        MenuItem(Icons.Filled.Person, "Perfil", "perfil", selected, onSelect)
        MenuItem(Icons.Filled.Photo, "Fotos", "fotos", selected, onSelect)
        MenuItem(Icons.Filled.PlayCircle, "Video", "video", selected, onSelect)
        MenuItem(Icons.Filled.Language, "Web", "web", selected, onSelect)
        MenuItem(Icons.Filled.TouchApp, "Botones", "botones", selected, onSelect)
    }
}

@Composable
fun MenuItem(
    icon: ImageVector,
    label: String,
    screen: String,
    selected: String,
    onSelect: (String) -> Unit
) {
    val isSelected = selected == screen
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                color = if (isSelected) Color(0xFF388E3C) else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelect(screen) }
            .padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
