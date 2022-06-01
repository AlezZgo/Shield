package ui.screens.main

import androidx.compose.runtime.mutableStateOf
import data.db.models.Person
import data.db.tables.Persons
import data.db.tables.Persons.toPerson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MainViewModel {

    private val _persons = MutableStateFlow(emptyList<Person>())
    val persons get() = _persons.asStateFlow()

    val requestText = mutableStateOf("")

    var expanded = mutableStateOf(false)

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun refresh() {
        coroutineScope.launch {
            _persons.emit(transaction {
                Persons.selectAll().andWhere {
                    Persons.name.like("%${requestText.value}%")
                }.map(::toPerson)
            })

        }
    }

}

