package ui.screens

import data.db.models.Person
import data.db.tables.Persons
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MainViewModel(
    private val db: Database
) {

    fun persons(): List<Person> = transaction(db) {
        Persons.selectAll().map {
            Persons.toPerson(it)
        }
    }

}

