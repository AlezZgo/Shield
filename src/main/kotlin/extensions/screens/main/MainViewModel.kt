package extensions.screens.main

import data.db.models.params.core.FilterParam
import data.db.tables.CustomTable
import extensions.toRussian
import io.github.evanrupert.excelkt.Sheet
import io.github.evanrupert.excelkt.workbook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.jetbrains.exposed.sql.Table
import ui.views.UIModel

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

    fun edit(oldModel: UIModel, newModel: UIModel, table: CustomTable) {
        coroutineScope.launch {
            table.edit(oldModel, newModel)
            refresh()
        }
    }

    fun add(uiModel: UIModel, table: CustomTable) {
        coroutineScope.launch {
            table.add(uiModel)
            refresh()
        }
    }

    fun exportCurrentDataToExcel() {

        coroutineScope.launch {
            workbook {
                sheet(currentTable.value.tableName.toRussian()) {
                    row(headerStyle()) {
                        _commons.value.first().params.forEach {
                            cell(it.first)
                        }
                    }
                    _commons.value.forEach{
                        row() {
                            it.params.forEachIndexed{index, pair ->
                                cell(pair.second)
                            }
                        }
                    }
                }
            }.write("${currentTable.value.tableName.toRussian()} ${currentTable.value.hashCode()}.xlsx")
        }
    }

    fun Sheet.headerStyle(): XSSFCellStyle = createCellStyle {
        setFont(createFont {
            fontName = "Roboto"
            color = IndexedColors.BLACK.index
        })
        fillPattern = FillPatternType.SOLID_FOREGROUND
        fillForegroundColor = IndexedColors.AQUA.index
    }

}

