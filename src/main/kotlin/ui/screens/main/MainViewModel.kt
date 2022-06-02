package ui.screens.main

import androidx.compose.runtime.mutableStateOf
import data.db.models.UIModel
import data.db.tables.UITable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

class MainViewModel(val tables: List<Table>) {

    var currentTable = MutableStateFlow(tables.first())

    var filters = MutableStateFlow(mutableListOf<Pair<Column<*>, String>>())

    private val _commons = MutableStateFlow(emptyList<UIModel>())
    val commons get() = _commons.asStateFlow()

    val requestText = mutableStateOf("")

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun refresh() {
        coroutineScope.launch {
            _commons.emit(
                (currentTable.value as UITable).selected(requestText.value)
            )
        }
    }

    fun initStartFiltersState(columns: List<Column<*>>) {
        coroutineScope.launch {
            val newFilters = mutableListOf<Pair<Column<*>, String>>()

            columns.forEach { column ->
                newFilters.add(column to "")
            }

            filters.emit(
                newFilters
            )

        }
    }

    fun setFilterState(newState: Pair<Column<*>, String>) {
        val newList = mutableListOf<Pair<Column<*>, String>>()
        newList.replaceAll {
            if (it.first == newState.first) newState else it
        }

        coroutineScope.launch {
            filters.emit(newList)
        }
    }


}

