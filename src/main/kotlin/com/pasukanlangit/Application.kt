package com.pasukanlangit

import com.pasukanlangit.db.configureDb
import io.ktor.application.*
import com.pasukanlangit.plugins.*
import org.ktorm.database.Database

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureDb()
}
