package data.db.models.params.core

import org.jetbrains.exposed.sql.Column

interface FilterParam<T> : Like {
    val name : String
    val column: Column<T>
    val param : T
}
