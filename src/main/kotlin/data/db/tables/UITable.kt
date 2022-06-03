package data.db.tables

import data.db.models.UIModel
import data.db.models.params.core.Delete
import org.jetbrains.exposed.sql.ResultRow

interface UITable : Filter  {
    fun toUI(row: ResultRow) : UIModel
}