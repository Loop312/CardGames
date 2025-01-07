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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    mainDeck.setupDeck()
    Box(Modifier.fillMaxSize().background(Color.DarkGray)){
        Column(modifier = Modifier.fillMaxWidth()) {
            mainDeck.displayAsText()
            Button(onClick = {mainDeck.shuffle()}) {
                Text("Shuffle")
            }
            showDeck()
        }
    }
}