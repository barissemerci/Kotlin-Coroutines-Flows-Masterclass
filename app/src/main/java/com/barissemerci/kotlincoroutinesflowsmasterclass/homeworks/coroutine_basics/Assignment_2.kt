package com.barissemerci.kotlincoroutinesflowsmasterclass.homeworks.coroutine_basics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

/*
[Medium] Assignment #2
Once woken up by the birds, you enjoy listening to them for a while.
Afterward, you must prepare for the day, so after listening to the birds for a while,
you close your window and get ready.
Instructions
Extend the previous assignment by removing the limitation of only printing four times to the console;
each coroutine can print indefinitely now.
Add a mechanism to cancel all running coroutines after 10 seconds.
 */

class Assignment_2 {
    fun main() {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val job = coroutineScope.launch {
            launch {
                while (true) {
                    println("Coo")
                    delay(1000)
                }
            }
            launch {
                while (true) {
                    println("Caw")
                    delay(2000)
                }
            }
            launch {
                while (true) {
                    println("Chirp")
                    delay(3000)
                }
            }
            delay(10.seconds)
            cancel()
        }
    }
}