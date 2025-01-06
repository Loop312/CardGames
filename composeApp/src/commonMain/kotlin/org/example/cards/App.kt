package org.example.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cards.composeapp.generated.resources.Res
import cards.composeapp.generated.resources.compose_multiplatform

val mainDeck = Deck()
@Composable
@Preview
fun App() {
    mainDeck.setupDeck()
    Box(Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxWidth()) {
            mainDeck.displayAsText()
            Button(onClick = {mainDeck.shuffle()}) {
                Text("Shuffle")
            }
            for (cards in mainDeck.cards) {
                cards.display()
            }
        }
    }
}