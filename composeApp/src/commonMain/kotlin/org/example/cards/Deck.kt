package org.example.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Deck {
    //var cards by mutableStateOf(emptyArray<Card>())
    var hasJoker by mutableStateOf(false)
    var cards = mutableListOf<Card>()
    var shuffling by mutableStateOf(false)

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
        }
        cards.first().flipped = false
        return cards.removeFirst()
    }

    @Composable
    fun showDeck(){
        for (card in cards) {
            card.flipped = false
        }
        var rows = minOf(13, cards.size)
        var columns = if (cards.size > 1) (cards.size + rows - 1) / rows // Ceiling division
        else 1

        // Optimize for a more square-like layout if possible
        while (columns > 1 && (rows - 1) * columns >= cards.size) {
            columns--
            rows = (cards.size + columns - 1) / columns
        }
        if (!shuffling) {
            Column {
                for (i in 1..columns) {
                    Row {
                        for (j in 1..rows) {
                            val index = (j - 1) + (i - 1) * rows
                            if (index < cards.size - (if(hasJoker) 2 else 0)) {
                                cards[index].display()
                            }
                        }
                    }
                }
                if (hasJoker) {
                    Row {
                        cards[52].display()
                        cards[53].display()
                    }
                }
            }
        }
    }

    @Composable
    fun showFaceDownDeck() {
        for (card in cards) {
            card.flipped = true
        }
        if (!shuffling) {
            for (i in 0..cards.size - 1) {
                Box(Modifier.offset(0.dp, i.dp)) {
                    cards[i].display()
                }
            }
        }
    }

    override fun toString(): String {
        return "$cards"
    }
}
val testDeck = Deck()