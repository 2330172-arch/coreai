package com.coreai.coreai.notifications

expect object NotificationManager {
    fun showNotification(
        title: String,
        message: String
    )
}