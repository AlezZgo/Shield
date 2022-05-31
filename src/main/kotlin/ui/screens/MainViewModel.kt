package ui.screens

import data.db.models.Person
import data.db.tables.Persons
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.skia.impl.Log
import java.lang.RuntimeException

class MainViewModel(
    private val db: Database
) {

    fun persons(): List<Person> =
        try {
            transaction(db) {
                Persons.selectAll().map {
                    Persons.toPerson(it)
                }
            }
        }catch (e: Exception){
            throw e
        }


}

