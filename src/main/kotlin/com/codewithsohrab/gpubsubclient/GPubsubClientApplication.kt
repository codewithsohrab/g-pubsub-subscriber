package com.codewithsohrab.gpubsubclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GPubsubClientApplication

fun main(args: Array<String>) {
    runApplication<GPubsubClientApplication>(*args)
}
