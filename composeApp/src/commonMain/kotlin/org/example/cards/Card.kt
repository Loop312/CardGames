package org.example.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cards.composeapp.generated.resources.Res
import cards.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

class Card(number: Int, type: Int) {
    val suit = numToSuit(type)
    val suitSign = when (suit) {
        "Clubs" -> "♣"
        "Diamonds" -> "♦"
        "Hearts" -> "♥"
        "Spades" -> "♠"
        else -> "Joker"
    }
    //setup values
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
        else -> "Joker"
    }
    //setup images
    val image = when (number) {
        1 -> when (suit) {
            "Clubs" -> Res.drawable.ace_of_clubs
            "Diamonds" -> Res.drawable.ace_of_diamonds
            "Hearts" -> Res.drawable.ace_of_hearts
            else -> Res.drawable.ace_of_spades
        }

        2 -> when (suit) {
            "Clubs" -> Res.drawable._2_of_clubs
            "Diamonds" -> Res.drawable._2_of_diamonds
            "Hearts" -> Res.drawable._2_of_hearts
            else -> Res.drawable._2_of_spades
        }

        3 -> when (suit) {
            "Clubs" -> Res.drawable._3_of_clubs
            "Diamonds" -> Res.drawable._3_of_diamonds
            "Hearts" -> Res.drawable._3_of_hearts
            else -> Res.drawable._3_of_spades
        }

        4 -> when (suit) {
            "Clubs" -> Res.drawable._4_of_clubs
            "Diamonds" -> Res.drawable._4_of_diamonds
            "Hearts" -> Res.drawable._4_of_hearts
            else -> Res.drawable._4_of_spades
        }

        5 -> when (suit) {
            "Clubs" -> Res.drawable._5_of_clubs
            "Diamonds" -> Res.drawable._5_of_diamonds
            "Hearts" -> Res.drawable._5_of_hearts
            else -> Res.drawable._5_of_spades
        }

        6 -> when (suit) {
            "Clubs" -> Res.drawable._6_of_clubs
            "Diamonds" -> Res.drawable._6_of_diamonds
            "Hearts" -> Res.drawable._6_of_hearts
            else -> Res.drawable._6_of_spades
        }

        7 -> when (suit) {
            "Clubs" -> Res.drawable._7_of_clubs
            "Diamonds" -> Res.drawable._7_of_diamonds
            "Hearts" -> Res.drawable._7_of_hearts
            else -> Res.drawable._7_of_spades
        }

        8 -> when (suit) {
            "Clubs" -> Res.drawable._8_of_clubs
            "Diamonds" -> Res.drawable._8_of_diamonds
            "Hearts" -> Res.drawable._8_of_hearts
            else -> Res.drawable._8_of_spades
        }

        9 -> when (suit) {
            "Clubs" -> Res.drawable._9_of_clubs
            "Diamonds" -> Res.drawable._9_of_diamonds
            "Hearts" -> Res.drawable._9_of_hearts
            else -> Res.drawable._9_of_spades
        }

        10 -> when (suit) {
            "Clubs" -> Res.drawable._10_of_clubs
            "Diamonds" -> Res.drawable._10_of_diamonds
            "Hearts" -> Res.drawable._10_of_hearts
            else -> Res.drawable._10_of_spades
        }

        11 -> when (suit) {
            "Clubs" -> Res.drawable.jack_of_clubs2
            "Diamonds" -> Res.drawable.jack_of_diamonds2
            "Hearts" -> Res.drawable.jack_of_hearts2
            else -> Res.drawable.jack_of_spades2
        }

        12 -> when (suit) {
            "Clubs" -> Res.drawable.queen_of_clubs2
            "Diamonds" -> Res.drawable.queen_of_diamonds2
            "Hearts" -> Res.drawable.queen_of_hearts2
            else -> Res.drawable.queen_of_spades2
        }

        13 -> when (suit) {
            "Clubs" -> Res.drawable.king_of_clubs2
            "Diamonds" -> Res.drawable.king_of_diamonds2
            "Hearts" -> Res.drawable.king_of_hearts2
            else -> Res.drawable.king_of_spades2
        }
        else -> Res.drawable.red_joker
    }

    var selected by mutableStateOf(false)
    var flipped by mutableStateOf(false)

    override fun toString(): String {
        return "$value of $suitSign"
    }

    @Composable
    fun display(){
        Box(Modifier.size(if (selected) 150.dp else 100.dp)) {
            if (!flipped) {
                Image(
                    painterResource(image),
                    contentDescription = toString(),
                    modifier = Modifier.clickable { selected = !selected }
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

fun numToSuit(suit: Int): String {
    return when (suit) {
        1 -> "Clubs"
        2 -> "Diamonds"
        3 -> "Hearts"
        4 -> "Spades"
        else -> "Joker"
    }
}