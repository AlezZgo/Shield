package data.db.models.params.core

import org.jetbrains.exposed.sql.Query

interface Delete {
    fun delete(query: Query): Query
}
