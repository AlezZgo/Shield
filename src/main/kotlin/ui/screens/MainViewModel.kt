package ui.screens

import androidx.compose.runtime.mutableStateOf
import data.db.models.Person
import data.db.tables.Persons
import data.db.tables.Persons.toPerson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MainViewModel {

    private val _persons = MutableStateFlow(emptyList<Person>())
    val persons get() = _persons.asStateFlow()

    val requestText = mutableStateOf("")

    suspend fun refresh() {
        _persons.emit(transaction{
            Persons.selectAll().map(::toPerson)
        })

    }

}

