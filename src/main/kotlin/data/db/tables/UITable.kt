package data.db.tables

import data.db.models.UIModel
import org.jetbrains.exposed.sql.ResultRow

interface UITable : Filter {
    fun toUI(row: ResultRow): UIModel
}