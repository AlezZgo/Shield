package data.db.tables

import ui.views.UIModel

interface Add {
    suspend fun add(uiModel: UIModel)
}
