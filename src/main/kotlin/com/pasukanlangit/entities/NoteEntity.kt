package com.pasukanlangit.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

object NoteEntity: Table<Nothing>("note") {
    val id = int("id").primaryKey()
    val note = text("note")
}