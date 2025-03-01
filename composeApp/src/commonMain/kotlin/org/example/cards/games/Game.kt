package org.example.cards.games

import androidx.compose.runtime.*
import org.example.cards.Deck

open class Game {
    var playingGame by mutableStateOf(false)
    open val name = ""
    var mainDeck = Deck()

    open fun play() {
        mainDeck.setupDeck()
    }
}