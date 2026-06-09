package com.coreai.coreai.models

import kotlinx.serialization.Serializable

@Serializable
data class Emergency(
    val id: Int,
    val usuario: String,
    val mensaje: String,
    val fecha: String
)