package data.db.tables

import data.db.models.Relative
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object RelativesTable : IntIdTable() {
    val name: Column<String> = varchar("name", 20)
    val relationDegree: Column<String> = varchar("relationDegree", 30)
    val employment: Column<String> = varchar("employment", 40)
    val birthDay: Column<Data> = float("weight")
    val address: Column<Int> = integer("address_id")

    fun toRelative(row: ResultRow) = Relative(
        name = row[name],
        age = row[age],
        weight = row[weight],
        address = row[address]
    )

}