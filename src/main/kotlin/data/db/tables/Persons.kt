package data.db.tables

import data.db.models.Person
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object Persons : IntIdTable() {
    val name: Column<String> = varchar("name", 20)
    val age: Column<Int> = integer("age")
    val weight: Column<Float> = float("weight")
    val address: Column<Int> = integer("address_id")

    fun toPerson(row: ResultRow) = Person(
        name = row[name],
        age = row[age],
        weight = row[weight],
        address = row[address]
    )

}