@file:OptIn(DelicateCoroutinesApi::class)

package com.barissemerci.kotlincoroutinesflowsmasterclass.homeworks.coroutine_basics

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/*
[Easy] Assignment #1
Each morning, you wake up to the sound of birds. Over time, you’ve noticed three unique bird sounds, each repeating at a different pace. One bird makes a sound every second, the other every 2 seconds, and the last every 3 seconds.
Instructions
Recreate the timing of each bird’s sounds using a single coroutine for each bird. Each coroutine should only print four times before completing.
The first bird makes a “Coo” sound.
The second bird makes a “Caw” sound.
The last bird makes a “Chirp” sound.
*/

class Assignment_1 {
    fun main() {
        GlobalScope.launch {
            launch {
                repeat(4) {
                    println("Coo")
                    delay(1000)
                }
            }
            launch {
                repeat(4) {
                    println("Caw")
                    delay(2000)
                }
            }
            launch {
                repeat(4) {
                    println("Chirp")
                    delay(3000)
                }
            }
        }

    }
}