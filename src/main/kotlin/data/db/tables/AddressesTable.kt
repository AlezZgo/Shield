package data.db.tables

import data.db.models.UIModel
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object AddressesTable : IntIdTable(),UITable {
    val address: Column<String> = varchar("address", 100)

    override fun toUI(row: ResultRow) = UIModel(listOf(
        "Адрес" to row[address]
    ))


}
