package com.coreai.coreai.repository

import com.coreai.coreai.models.Emergency
import com.coreai.coreai.network.EmergencyService

class EmergencyRepository {
    private val service = EmergencyService()

    companion object {
        private val localCacheList = mutableListOf<Emergency>()
    }

    // Nombre exacto requerido por la capa de interfaz UI
    suspend fun sendAndSaveEmergency(emergency: Emergency): Boolean {
        val networkResult = service.sendEmergency(emergency)
        localCacheList.add(emergency)
        return networkResult
    }

    // Nombre exacto requerido por la LazyColumn
    fun getLocalHistory(): List<Emergency> = localCacheList.toList()
}