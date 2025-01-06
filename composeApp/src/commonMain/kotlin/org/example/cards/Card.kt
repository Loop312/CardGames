package org.example.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cards.composeapp.generated.resources.Res
import cards.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

class Card(number: Int, type: Int) {
    val suit = numToSuit(type)
    val suitSign = when (suit) {
        "Clubs" -> "♣"
        "Diamonds" -> "♦"
        "Hearts" -> "♥"
        else -> "♠"
    }
    val value = when (number) {
        1 -> "A"
        2 -> "2"
        3 -> "3"
        4 -> "4"
        5 -> "5"
        6 -> "6"
        7 -> "7"
        8 -> "8"
        9 -> "9"
        10 -> "10"
        11 -> "J"
        12 -> "Q"
        13 -> "K"
        else -> "Unknown"
    }
    val image = when (number) {
        1 -> "ace_of_clubs"
        2 -> "two_of_clubs"
        3 -> "three_of_clubs"
        4 -> "four_of_clubs"
        5 -> "five_of_clubs"
        6 -> "six_of_clubs"
        7 -> "seven_of_clubs"
        8 -> "eight_of_clubs"
        9 -> "nine_of_clubs"
        10 -> "ten_of_clubs"
        11 -> "jack_of_clubs"
        12 -> "queen_of_clubs"
        13 -> "king_of_clubs"
        else -> "unknown"
    }
    var selected by mutableStateOf(false)
    var flipped by mutableStateOf(false)

    override fun toString(): String {
        return "$value of $suitSign"
    }

    @Composable
    fun display(){
        Box(Modifier.size(100.dp)) {
            if (flipped) {
                Image(
                    painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = ""
                )
            } else {
                Image(
                    painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = ""
                )
            }
        }
    }
}


class Deck {
    var cards by mutableStateOf(emptyArray<Card>())
    var shuffling by mutableStateOf(false)
    fun setupDeck(){
        for (suit in 1..4) {
            for (value in 1..13) {
                cards += Card(value, suit)
            }
        }
    }

    fun shuffle() {
        shuffling = true
        cards.shuffle()
        println(cards.contentToString())
        shuffling = false
    }

    @Composable
    fun displayAsText(){
        if (!shuffling) {
            Text(cards.contentToString())
        }
    }
}

fun numToSuit(suit: Int): String {
    return when (suit) {
        1 -> "Clubs"
        2 -> "Diamonds"
        3 -> "Hearts"
        else -> "Spades"
    }
}