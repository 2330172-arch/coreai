package com.coreai.coreai.notifications

/**
 * Configuración general de las notificaciones de CORE AI.
 *
 * Esta clase contiene únicamente constantes que serán utilizadas
 * por NotificationHelper (Rama 1) y por la interfaz (Rama 2).
 *
 * Administrador:
 * Preparar la infraestructura del sistema de notificaciones.
 */
object NotificationConfig {

    /**
     * Identificador único del canal de notificaciones.
     */
    const val CHANNEL_ID = "coreai_notifications"

    /**
     * Nombre visible del canal.
     */
    const val CHANNEL_NAME = "CORE AI"

    /**
     * Descripción del canal.
     */
    const val CHANNEL_DESCRIPTION =
        "Canal utilizado para mostrar las notificaciones de CORE AI"

    /**
     * Identificador único para cada notificación.
     */
    const val NOTIFICATION_ID = 1001
}