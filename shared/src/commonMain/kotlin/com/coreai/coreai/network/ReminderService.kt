package com.coreai.coreai.network

import com.coreai.coreai.models.Reminder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ReminderService {

    private val client = HttpClient() {

        install(ContentNegotiation) {

            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    suspend fun getReminders():
            List<Reminder> {

        return try {

            client.get(
                "https://https-coreia-backenduprailwayapp-production.up.railway.app/recordatorios"
            ).body()

        } catch (e: Exception) {

            listOf(
                Reminder(
                    id = 1,
                    titulo = "cita medica",
                    hora = "12:00",
                    fecha = "",
                    activo = false
                )
            )
        }
    }
}