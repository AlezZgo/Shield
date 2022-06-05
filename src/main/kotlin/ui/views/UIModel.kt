package ui.views

import org.jetbrains.exposed.sql.Table

data class UIModel(val params: List<Pair<String, String>>) {

    companion object {
        fun emptyModel(table: Table): UIModel = UIModel(
            table.columns.drop(1).map {
                it.name to ""
            }
        )
    }
}
