package ui.screens.main

import androidx.compose.runtime.mutableStateOf
import data.db.models.UIModel
import data.db.tables.AddressesTable
import data.db.tables.PersonsTable

import data.db.tables.RelativesTable
import data.db.tables.UITable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MainViewModel(val tables: List<Table>) {

    var currentTable = MutableStateFlow(tables.first())

    private val _commons = MutableStateFlow(emptyList<UIModel>())
    val commons get() = _commons.asStateFlow()

    val requestText = mutableStateOf("")

    var expanded = mutableStateOf(false)

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun refresh() {
        coroutineScope.launch {
            _commons.emit(
                (currentTable.value as UITable).selected(requestText.value)
            )
        }
    }

}

