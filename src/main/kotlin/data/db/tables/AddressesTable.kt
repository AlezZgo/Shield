package data.db.tables

import data.db.models.UIModel
import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object AddressesTable : IntIdTable(),UITable {
    val address: Column<String> = varchar("address", 100)

    override fun toUI(row: ResultRow) = UIModel(listOf(
        "Адрес" to row[address]
    ))

    override suspend fun filtered(params: MutableSet<FilterParam<*>>): List<UIModel> {
        return transaction {
            val all = selectAll()
            params.forEach { param->
                param.like(all)
            }
            return@transaction all.map(::toUI)
        }
    }


}
