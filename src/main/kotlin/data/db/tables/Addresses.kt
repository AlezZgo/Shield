package data.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Addresses : IntIdTable() {
    val name: Column<String> = varchar("address", 100)

}
