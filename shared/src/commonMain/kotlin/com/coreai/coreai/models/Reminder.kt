package com.coreai.coreai.models

import kotlinx.serialization.Serializable

@Serializable
data class Reminder(
    val id: Int,
    val titulo: String,
    val hora: String
)