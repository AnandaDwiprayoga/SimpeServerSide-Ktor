package com.pasukanlangit.db

import com.pasukanlangit.entities.NoteEntity
import io.ktor.application.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun Application.configureDb(){
    val database = Database.connect(
        url = "jdbc:mysql://192.168.1.8/persewaan",
        driver ="com.mysql.cj.jdbc.Driver",
        user = "remotemac"
    )

    //TODO: insert value
    //insertNotes(database)

    //TODO: to read value from table
    //getAllNotes(database)

    //TODO: update specific row
    //updateNote(database, 5)

    //TODO: delete specific row
    //deleteNote(database, 1)

}

fun deleteNote(database: Database, idNote: Int) {
    val effectedRow = database.delete(NoteEntity){
        it.id eq idNote
    }

    if(effectedRow != 0){
        println("value successfully deleted")
    }else{
        println("id $idNote not found")
    }

}

fun insertNotes(database: Database){
    //insert value
    database.insert(NoteEntity){
        set(it.note, "learn ktor")
    }
}

fun getAllNotes(database: Database){
    //to read value from table
    val notes = database.from(NoteEntity).select()
    println("Value before updating")
    for(note in notes){
        println("id = ${note[NoteEntity.id]}, value = ${note[NoteEntity.note]}")
    }
}

fun updateNote(database: Database, idNoteUpdate: Int){
    val updateEffected = database.update(NoteEntity){
        set(it.note, "This value after updating")
        where {
            it.id eq idNoteUpdate
        }
    }

    if(updateEffected != 0){
        val notesUpdated = database.from(NoteEntity).select().where {
            NoteEntity.id eq idNoteUpdate
        }

        println("Success to update note with id = $idNoteUpdate")
        println()
        println("Value before updating")

        notesUpdated.forEach { noteUpdated ->
            println("After update: id = ${noteUpdated[NoteEntity.id]}, value = ${noteUpdated[NoteEntity.note]}")
        }
    }else{
        println("id not found")
    }
}