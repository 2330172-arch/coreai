package com.coreai.coreai.notifications

import android.content.Context

actual object NotificationManager {

    private var context: Context? = null

    fun initialize(appContext: Context) {
        context = appContext.applicationContext
        NotificationHelper.createNotificationChannel(context!!)
    }

    actual fun showNotification(
        title: String,
        message: String
    ) {
        context?.let {
            NotificationHelper.showNotification(
                it,
                title,
                message
            )
        }
    }
}