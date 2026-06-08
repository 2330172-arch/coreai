package com.coreai.coreai

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

import com.coreai.coreai.screens.HomeScreen
import com.coreai.coreai.screens.RemindersScreen

@Composable
fun App() {

    var pantalla by remember {
        mutableStateOf("home")
    }

    MaterialTheme {

        when (pantalla) {

            "home" -> HomeScreen(
                abrirRecordatorios = {
                    pantalla = "recordatorios"
                }
            )

            "recordatorios" -> RemindersScreen()
        }
    }
}