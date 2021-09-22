package com.pasukanlangit.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    //give default value when user not send key password
    val password: String ?= ""
)
