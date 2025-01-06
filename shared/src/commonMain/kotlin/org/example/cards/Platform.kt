package org.example.cards

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform