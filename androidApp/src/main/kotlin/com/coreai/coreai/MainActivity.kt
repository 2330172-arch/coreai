package com.coreai.coreai

import android.os.Bundle

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.content.Context
import com.coreai.coreai.notifications.NotificationConfig

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.coreai.coreai.notifications.NotificationManager as CoreNotificationManager

class MainActivity : ComponentActivity() {

    /**
     * Solicita el permiso para mostrar notificaciones
     * en Android 13 o superior.
     */
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {
                // Permiso concedido.
            } else {
                // El usuario rechazó el permiso.
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {


        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        CoreNotificationManager.initialize(applicationContext)
        createNotificationChannel()

        requestNotificationPermission()


        setContent {

            App()

        }

    }

    /**
     * Crea el canal de notificaciones de CORE AI.
     *
     * Android requiere que todas las notificaciones pertenezcan
     * a un canal desde Android 8 (API 26).
     *
     * Este método solamente crea el canal una vez.
     */
    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                NotificationConfig.CHANNEL_ID,
                NotificationConfig.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {

                description = NotificationConfig.CHANNEL_DESCRIPTION
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Solicita el permiso POST_NOTIFICATIONS
     * únicamente en Android 13 o superior.
     */
    private fun requestNotificationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            when {

                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {

                    // Ya existe permiso.
                }

                else -> {

                    notificationPermissionLauncher.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )

                }

            }

        }

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}