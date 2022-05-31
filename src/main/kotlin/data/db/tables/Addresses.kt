package data.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Addresses : IntIdTable() {
    val name: Column<String> = varchar("address", 100)
}