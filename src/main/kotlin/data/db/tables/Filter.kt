package data.db.tables

import data.db.models.UIModel
import data.db.models.params.core.FilterParam

interface Filter {
    suspend fun filtered(params: MutableSet<FilterParam<*>>): List<UIModel>
}