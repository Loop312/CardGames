package org.example.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Deck {
    //var cards by mutableStateOf(emptyArray<Card>())
    var hasJoker by mutableStateOf(false)
    var cards = mutableListOf<Card>()

    fun setupDeck(){
        cards = mutableListOf()
        for (suit in 1..4) {
            for (value in 1..13) {
                cards += Card(value, suit)
            }
        }
        if (hasJoker) {
            cards += Card(14, 14)
            cards += Card(14, 14)
        }
    }

    fun shuffle() {
        shuffling = true
        cards.shuffle()
        println(cards)
        //println(cards.)
        shuffling = false
    }

    @Composable
    fun displayAsText(){
        if (!shuffling) {
            Text("$cards")
        }
    }

    //make something proper to check if deck is empty
    fun handCard(): Card {
        if (cards.isEmpty()) {
            println("Deck is empty")
            setupDeck()
        }
        return cards.removeAt(0)
    }
}

val testDeck = Deck()
var shuffling by mutableStateOf(false)

@Composable
fun showDeck(){
    if (!shuffling) {
        Column {
            for (i in 1..4) {
                Row {
                    for (j in 1..13) {
                        testDeck.cards[(j - 1) + (i - 1) * 13].display()
                    }
                }
            }
            if (testDeck.hasJoker) {
                Row {
                    testDeck.cards[52].display()
                    testDeck.cards[53].display()
                }
            }
        }
    }
}