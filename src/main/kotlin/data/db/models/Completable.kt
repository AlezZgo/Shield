package data.db.models

import data.db.models.params.core.FilterParam

interface Completable {

    suspend fun full() : List<FilterParam<*>>
}