package com.coreai.coreai

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform