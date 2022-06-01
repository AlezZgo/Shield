package data.db.models

import data.db.models.params.core.Param

interface Completable {

    suspend fun full() : List<Param<*>>
}