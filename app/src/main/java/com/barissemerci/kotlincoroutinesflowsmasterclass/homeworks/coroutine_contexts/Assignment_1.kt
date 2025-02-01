package com.barissemerci.kotlincoroutinesflowsmasterclass.homeworks.coroutine_contexts

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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
[Easy] Assignment #1

Throughout your day, you came up with a name for each bird:
Tweety, Zazu, and Woodstock.
Instructions
Continuing from the previous program you’ve written in Coroutine Basics,
give each coroutine a name and print them out alongside the sounds they make.
*/
@Composable
fun Assignment_1(modifier: Modifier = Modifier) {
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
        launch(CoroutineName("Tweety")) {
            while (true) {
                println("${birds[0].sound}, ${coroutineContext[CoroutineName]?.name}")
                delay(birds[0].delay)

            }
        }
    }
    Text(text = birds[0].sound)
}


@Composable
fun BirdCawSound() {
    LaunchedEffect(true) {
        launch(CoroutineName("Zazu")) {
            while (true) {
                println("${birds[1].sound}, ${coroutineContext[CoroutineName]?.name}")
                delay(birds[1].delay)

            }
        }
    }
    Text(text = birds[1].sound)
}

@Composable
fun BirdChirpSound() {
    LaunchedEffect(true) {
        launch(CoroutineName("Woodstock")) {
            while (true) {
                println("${birds[2].sound}, ${coroutineContext[CoroutineName]?.name}")
                delay(birds[2].delay)

            }
        }
    }
    Text(text = birds[2].sound)
}