package data.db.tables

import data.db.models.params.core.Delete
import ui.views.UIModel
import org.jetbrains.exposed.sql.ResultRow

interface CustomTable : Filter,Delete, Edit,Add {
    fun toUI(row: ResultRow): UIModel

    override suspend fun add(uiModel: UIModel)
}