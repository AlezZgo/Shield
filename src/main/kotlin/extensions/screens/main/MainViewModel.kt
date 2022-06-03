package extensions.screens.main

import ui.views.UIModel
import data.db.models.params.core.FilterParam
import data.db.tables.CustomTable
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

    init {
        refresh()
    }

    fun refresh() {
        coroutineScope.launch {
            _commons.emit(
                (currentTable.value as CustomTable).filtered(filters.value).sortedBy {
                    it.params.first().second
                }
            )
        }
    }

    fun delete(model: UIModel, table: CustomTable) {
        coroutineScope.launch {
            table.delete(model)
            refresh()
        }
    }

    fun edit(oldModel: UIModel,newModel: UIModel, table: CustomTable) {
        coroutineScope.launch {
            table.edit(oldModel,newModel)
            refresh()
        }
    }


}

