package com.coreai.coreai.network

import com.coreai.coreai.models.Reminder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ReminderService {

    private val client = HttpClient()

    suspend fun getReminders(): List<Reminder> {

        return try {

            client.get(
                "https://TU-URL-RAILWAY/recordatorios"
            ).body()

        } catch (e: Exception) {

            listOf(
                Reminder(1, "Medicamento", "08:00"),
                Reminder(2, "Reunión", "10:30"),
                Reminder(3, "Llamada", "13:15")
            )
        }
    }
}