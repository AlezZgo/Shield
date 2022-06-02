package data.db.tables

import data.db.models.UIModel

interface Select {
    suspend fun selected(requestText : String) : List<UIModel>
}