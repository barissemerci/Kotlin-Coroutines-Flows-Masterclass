package com.barissemerci.kotlincoroutinesflowsmasterclass.homeworks.coroutine_cancellation.assignment_1

import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

object RemoteService {
    suspend fun uploadFile() {
        val chunks = List(400) { it }
        println("Uploading File")
        var index = 0
        while (index < chunks.size) {
            try {
                // Simulate uploading a single chunk of data
                delay(5)
                index++
                println("Progress: ${index * 100 / chunks.size}%")
            } catch (e: Exception) {
              //coroutineContext.ensureActive()
                if(e is CancellationException) throw e
                println("Error uploading file: ${e.message}")
            }
        }
        println("Upload Complete")
    }
}