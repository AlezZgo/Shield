package ui.views

import org.jetbrains.exposed.sql.Table

data class UIModel(val params: List<Pair<String, String>>) {

    companion object {
        fun emptyModel(table: Table): UIModel {

            val list = mutableListOf<Pair<String, String>>()

            table.columns.drop(1).forEach {
                list.add(it.name to "")
            }

            return UIModel(list.toList())
        }
    }
}