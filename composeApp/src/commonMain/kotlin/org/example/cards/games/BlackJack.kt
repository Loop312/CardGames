package org.example.cards.games

import androidx.compose.runtime.Composable
import org.example.cards.Card

class BlackJack: Game() {

    override val name = "BlackJack"

    var playerHand = mutableListOf<Card>()
    var dealerHand = mutableListOf<Card>()
    var playerScore = 0
    var dealerScore = 0
    var playerTurn = true

    var cardCounter = 0



    override fun play() {
        mainDeck.setupDeck()

    }

    fun dealCards() {
        playerHand += mainDeck.handCard()
        playerHand += mainDeck.handCard()
        dealerHand += mainDeck.handCard()
        dealerHand += mainDeck.handCard()
    }
}