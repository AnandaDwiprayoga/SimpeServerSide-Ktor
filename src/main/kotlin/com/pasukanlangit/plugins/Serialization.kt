package com.pasukanlangit.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json


fun Application.configureSerialization(){
    install(ContentNegotiation){
        json(
            //use this config to ignore if there are no key sent by client
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
}