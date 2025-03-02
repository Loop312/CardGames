package org.example.cards.games

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.cards.Card
import org.example.cards.finalScore
import org.example.cards.lastGameScore
import org.example.cards.playing

class BlackJack: Game() {
    var refresher by mutableStateOf(true)
    var restart by mutableStateOf(true)
    var buttonsClickable by mutableStateOf(true)
    override val name = "BlackJack"

    var playerHand = mutableListOf<Card>()
    var dealerHand = mutableListOf<Card>()
    var playerHandValue = 0
    var dealerHandValue = 0
    var playerScore = 0
    var dealerScore = 0
    var hit = false

    var cardCounter by mutableStateOf(0)

    @Composable
    override fun play() {
        if (restart) {
            playerHand = mutableListOf()
            dealerHand = mutableListOf()
            playerHandValue = 0
            dealerHandValue = 0
            playerScore = 0
            dealerScore = 0
            hit = false
            mainDeck.setupDeck()
            mainDeck.shuffle()
            restart = false
        }
        if (playingGame) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (refresher) {

                    mainDeck.showFaceDownDeck()
                    //dealer hand
                    Row(Modifier.align(Alignment.TopCenter)) {
                        for (card in dealerHand) {
                            card.display()
                        }
                    }

                    //player hand
                    Row(Modifier.align(Alignment.BottomCenter)) {
                        for (card in playerHand) {
                            card.display()
                        }
                    }

                    //scores
                    Column(Modifier.align(Alignment.CenterStart)) {
                        Text("Dealer score: $dealerScore")
                        Text("Dealer hand value: $dealerHandValue")
                        Text("Player hand value: $playerHandValue")
                        Text("Player score: $playerScore")
                    }
                    //card counter
                    Column(Modifier.align(Alignment.CenterEnd)) {
                        Text("Card counter (Hi-Lo): $cardCounter")
                    }
                }
                //hit and stand buttons
                Column {
                    if (buttonsClickable) {
                        //hit
                        Button(onClick = { hit() }) {
                            Text(if (hit) "Hit" else "Deal")
                        }
                        //if already hit
                        if (hit) {
                            //stand
                            Button(onClick = { stand() }) {
                                Text("Stand")
                            }
                        }
                    }
                }
            }
        }
    }

    fun dealCards(hand: MutableList<Card>) {
        try {
            hand += mainDeck.handCard()

            if (hand.size == 1) {
                hand += mainDeck.handCard()
            }
            playerHandValue = calculateHandValue(playerHand)
            dealerHandValue = calculateHandValue(dealerHand)
            refresh()
            println("dealer hand: $dealerHand")
            println("player hand: $playerHand")
            println("$mainDeck")
        } catch (e: Exception) {
            println(e)
            finalScore[0] += if (playerScore > dealerScore) 1 else 0
            finalScore[1] += if (playerScore < dealerScore) 1 else 0
            lastGameScore[0] = playerScore
            lastGameScore[1] = dealerScore

            playingGame = false
            playing = false

            restart = true
        }
    }

    fun calculateHandValue(hand: MutableList<Card>): Int {
        var value = 0
        var aces = 0
        for (card in hand) {
            if (card.value.first == 1) {
                aces++
            }
            else {
                value += if (card.value.first > 10) 10 else card.value.first
            }
        }
        for(i in 1..aces) {
            if (value + 11 <= 21) {
                value += 11
            } else {
                value += 1
            }
        }
        return value
    }

    fun reset() {
        if (dealerHandValue <= 21 && playerHandValue <= 21 && dealerHandValue > playerHandValue || dealerHandValue <= 21 && playerHandValue > 21) dealerScore++
        if (playerHandValue <= 21 && dealerHandValue <= 21 && playerHandValue > dealerHandValue || playerHandValue <= 21 && dealerHandValue > 21 ) playerScore++

        playerHand = mutableListOf()
        dealerHand = mutableListOf()
        playerHandValue = 0
        dealerHandValue = 0
        hit = false
        buttonsClickable = true

        refresh()
    }


    fun refresh() {
        refresher = !refresher
        refresher = !refresher
    }

    fun hit() {
        CoroutineScope(Dispatchers.Default).launch {
            buttonsClickable = false
            dealCards(playerHand)
            delay(500)
            if (dealerHandValue < 17) dealCards(dealerHand)
            hit = true
            mainDeck.shuffling = !mainDeck.shuffling
            mainDeck.shuffling = !mainDeck.shuffling
            buttonsClickable = true
            refresh()

            if (playerHandValue > 21 || dealerHandValue > 21) {
                stand()
            }
        }
    }
    fun stand() {
        CoroutineScope(Dispatchers.Default).launch {
            buttonsClickable = false
            while (dealerHandValue < 17) {
                dealCards(dealerHand)
                delay(1000)
            }
            delay(1000)
            hit = false
            reset()
        }
    }

    fun calculateCardCounter() {
        //cardCounter += if (card.value.first == 1 || card.value.first >= 10) -1 else if (card.value.first >= 2 && card.value.first <= 6) 1 else 0
    }
}