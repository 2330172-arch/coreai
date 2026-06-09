package com.coreai.coreai.navigation

import androidx.compose.runtime.*
import com.coreai.coreai.screens.*

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    when (currentScreen) {
        Screen.HOME -> {
            HomeScreen(
                abrirRecordatorios = {
                    currentScreen = Screen.REMINDERS
                },
                abrirEmergencia = {
                    currentScreen = Screen.EMERGENCY
                }
            )
        }
        Screen.EMERGENCY -> {
            EmergencyScreen()
        }
        Screen.REMINDERS -> {
            RemindersScreen()
        }
        Screen.HISTORY -> {
            HistoryScreen()
        }
        Screen.SETTINGS -> {
            SettingsScreen()
        }
    }
}