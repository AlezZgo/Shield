package data.db.tables

import ui.views.UIModel

interface Edit {
    suspend fun edit(oldModel: UIModel, newModel: UIModel)
}
