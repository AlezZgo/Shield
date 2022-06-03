package data.db.tables

import ui.views.UIModel
import data.db.models.params.core.FilterParam

interface Filter {
    suspend fun filtered(params: MutableSet<FilterParam<*>>): List<UIModel>
}