package data.db.tables

import data.db.models.UIModel
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object PersonsTable : IntIdTable(), UITable {
    val name: Column<String> = varchar("name", 100)
    val age: Column<Int> = integer("age")
    val weight: Column<Float> = float("weight")
    val address: Column<Int> = integer("address_id")

    override fun toUI(row: ResultRow) = UIModel(
        listOf(
            "ФИО" to row[name],
            "Возраст" to row[age].toString(),
            "Вес" to row[weight].toString(),
            "Адрес" to row[address].toString()
        )
    )

    override suspend fun selected(requestText: String): List<UIModel> {
        return transaction {
            selectAll().andWhere {
                name.like("%${requestText}%")
            }.map(::toUI)
        }
    }
}
