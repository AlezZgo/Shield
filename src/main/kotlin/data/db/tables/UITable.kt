package data.db.tables

import data.db.models.UIModel
import org.jetbrains.exposed.sql.ResultRow

interface UITable : Select  {
    fun toUI(row: ResultRow) : UIModel
}