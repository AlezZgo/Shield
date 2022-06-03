package data.db.models.params.core

import ui.views.UIModel

interface Delete {
    suspend fun delete(model: UIModel)
}
