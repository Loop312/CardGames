package org.example.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.cards.games.*
import org.jetbrains.compose.ui.tooling.preview.Preview


val BlackJack = BlackJack()
val Duel52 = Duel52()
val President = President()
val CrazyEights = CrazyEights()
val RatScrew = RatScrew()
val Regicide = Regicide()

val games = arrayOf(BlackJack, Duel52, President, CrazyEights, RatScrew, Regicide)
var playing = false

val testingDeck = true

@Composable
@Preview
fun App() {
    testDeck.setupDeck()
    Box(Modifier.fillMaxSize().background(Color.DarkGray)){
        if (testingDeck) {
            //home menu
            if (!playing) {
                Column {
                    for (i in 1..games.size) {
                        Button(onClick = { games[i - 1].play() }) {
                            Text(games[i - 1].name)
                        }
                    }
                }
            }
        }

        else {
            Column(modifier = Modifier.fillMaxWidth()) {
                testDeck.displayAsText()
                Button(onClick = { testDeck.shuffle() }) {
                    Text("Shuffle")
                }
                showDeck()
            }
        }
    }
}