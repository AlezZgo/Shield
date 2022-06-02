package data.db.models.params.core

import org.jetbrains.exposed.sql.Query

interface Like {
    fun like(query: Query): Query
}