package ui.screens.main

import data.db.models.UIModel
import data.db.models.params.core.FilterParam
import data.db.tables.UITable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.Table

class MainViewModel(val tables: List<Table>) {

    var currentTable = MutableStateFlow(tables.first())

    var filters = MutableStateFlow(mutableSetOf<FilterParam<*>>())

    private val _commons = MutableStateFlow(emptyList<UIModel>())
    val commons get() = _commons.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun refresh() {
        coroutineScope.launch {
            _commons.emit(
                (currentTable.value as UITable).filtered(filters.value).sortedBy {
                    it.params.first().second
                }
            )
        }
    }

//    fun delete(params: MutableSet<FilterParam<*>>) {
//        coroutineScope.launch {
//            (currentTable.value as UITable).remove(params)
//        }
//    }

}

