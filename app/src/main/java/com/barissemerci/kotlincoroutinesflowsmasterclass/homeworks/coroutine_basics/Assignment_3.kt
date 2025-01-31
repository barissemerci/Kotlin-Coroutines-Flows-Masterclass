package com.barissemerci.kotlincoroutinesflowsmasterclass.homeworks.coroutine_basics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barissemerci.kotlincoroutinesflowsmasterclass.ui.theme.KotlinCoroutinesFlowsMasterclassTheme
import kotlinx.coroutines.delay

/*
[Hard] Assignment #3
You decide to make a simple mobile application to simulate the birds’ sounds you hear in the morning.
After planning, you’ve decided on a single screen with three buttons.
Each button will show the bird's name on the screen and print its sound to the console.
Tapping a new button will replace the previous bird's name on the screen,
and only the new bird’s sounds will print to the console.
Instructions
Create a single-screen app using coroutines in Compose.
Add three buttons, each representing a different bird.
Create a composable function for each bird that displays the bird’s name
and launches a coroutine to print the bird's sound to the console.
Only render the composable of the bird represented by
the last button tapped and ensure only the selected bird's sounds are printed.
 */
@Composable
fun Assignment_3(modifier: Modifier = Modifier) {
    var selectedBird by remember {
        mutableStateOf(birds[0])
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (selectedBird) {
                birds[0] -> BirdCooSound()
                birds[1] -> BirdCawSound()
                birds[2] -> BirdChirpSound()
            }

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            birds.forEach {
                BirdButton(
                    onClick = {
                        selectedBird = it
                    },
                    bird = it
                )
            }
        }
    }
}

data class Bird(val sound: String, val delay: Long)

val birds = listOf(
                Bird("Coo", 1000),
                Bird("Caw", 2000),
                Bird("Chirp", 3000)
            )

@Composable
fun BirdButton(bird: Bird, onClick: () -> Unit) {
    Button(
        onClick = onClick
    ) {
        Text(text = bird.sound)
    }
}

@Composable
fun BirdCooSound() {
    LaunchedEffect(true) {
        while (true) {
            println(birds[0].sound)
            delay(birds[0].delay)

        }
    }
    Text(text = birds[0].sound)
}

@Composable
fun BirdCawSound() {
    LaunchedEffect(true) {
        while (true) {
            println(birds[1].sound)
            delay(birds[1].delay)

        }
    }
    Text(text = birds[1].sound)
}

@Composable
fun BirdChirpSound() {
    LaunchedEffect(true) {
        while (true) {
            println(birds[2].sound)
            delay(birds[2].delay)

        }
    }
    Text(text = birds[2].sound)
}

@Preview
@Composable
private fun Assignment_3Preview() {
    KotlinCoroutinesFlowsMasterclassTheme {
        Assignment_3()
    }
}