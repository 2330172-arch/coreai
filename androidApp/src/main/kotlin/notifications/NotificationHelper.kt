package com.coreai.coreai.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.coreai.coreai.R

object NotificationHelper {

    /**
     * Crea el canal de notificaciones.
     * Solo es necesario en Android 8 (API 26) o superior.
     */
    fun createNotificationChannel(
        context: Context
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                NotificationConfig.CHANNEL_ID,
                NotificationConfig.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {

                description =
                    NotificationConfig.CHANNEL_DESCRIPTION
            }

            val notificationManager =
                context.getSystemService(
                    NotificationManager::class.java
                )

            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Muestra una notificación local.
     */
    fun showNotification(
        context: Context,
        title: String,
        message: String
    ) {

        val notification =
            NotificationCompat.Builder(
                context,
                NotificationConfig.CHANNEL_ID
            )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(
                    NotificationCompat.PRIORITY_HIGH
                )
                .setAutoCancel(true)
                .build()

        try {
            NotificationManagerCompat
                .from(context)
                .notify(
                    NotificationConfig.NOTIFICATION_ID,
                    notification
                )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}