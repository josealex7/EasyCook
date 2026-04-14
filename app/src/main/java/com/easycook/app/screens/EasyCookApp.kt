package com.easycook.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@androidx.compose.runtime.Composable
fun EasyCookApp() {
    var selectedScreen by remember { mutableStateOf("inicio") }

    Row(modifier = Modifier.fillMaxSize()) {

        // Menú lateral 30%
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.3f)
                .background(Color(0xFF4CAF50))
        ) {
            SideMenu(
                selected = selectedScreen,
                onSelect = { selectedScreen = it }
            )
        }

        // Contenido dinámico 70%
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.7f)
                .background(Color.White)
        ) {
            when (selectedScreen) {
                "inicio"   -> InicioScreen()
                "perfil"   -> PerfilScreen()
                "fotos"    -> FotosScreen()
                "video"    -> VideoScreen()
                "web"      -> WebScreen()
                "botones"  -> BotonesScreen()
            }
        }
    }
}