package data.db.tables

import data.db.models.UIModel
import data.db.models.params.core.FilterParam

interface Remove {
    suspend fun remove(params: MutableSet<FilterParam<*>>)
}
