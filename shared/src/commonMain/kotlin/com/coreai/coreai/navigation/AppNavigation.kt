package com.coreai.coreai.navigation

import androidx.compose.runtime.*
import com.coreai.coreai.screens.*
import com.coreai.coreai.screens.HomeScreen
import com.coreai.coreai.screens.EmergencyScreen
import com.coreai.coreai.screens.RemindersScreen
import com.coreai.coreai.screens.HistoryScreen
import com.coreai.coreai.screens.SettingsScreen

@Composable
fun AppNavigation() {

    var currentScreen by remember {
        mutableStateOf(Screen.HOME)
    }

    when(currentScreen) {

        Screen.HOME ->
            HomeScreen()

        Screen.EMERGENCY ->
            EmergencyScreen()

        Screen.REMINDERS ->
            RemindersScreen()

        Screen.HISTORY ->
            HistoryScreen()

        Screen.SETTINGS ->
            SettingsScreen()
    }
}