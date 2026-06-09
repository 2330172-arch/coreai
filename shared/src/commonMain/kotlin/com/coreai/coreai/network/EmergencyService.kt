package com.coreai.coreai.network

import com.coreai.coreai.models.Emergency

class EmergencyService {
    // 🚂 URL BASE DE RAILWAY CONFIGURADA CORRECTAMENTE
    private val BASE_URL = "https://https-coreia-backenduprailwayapp-production.up.railway.app"

    /**
     * Consume el endpoint POST /emergencia asignado a la Rama 2
     */
    suspend fun sendEmergency(emergency: Emergency): Boolean {
        // Aquí el sistema concatena automáticamente la URL con el git branchendpoint de tu rama
        val endpointCompleto = "$BASE_URL/emergencia"

        println("Enviando petición HTTP POST a: $endpointCompleto")
        println("Datos del paquete SOS: ID=${emergency.id}, Mensaje=${emergency.mensaje}")

        // Simulación de respuesta exitosa del servidor de Railway (Devuelve true)
        return true
    }
}