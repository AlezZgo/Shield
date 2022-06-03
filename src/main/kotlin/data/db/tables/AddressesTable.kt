package data.db.tables

import ui.views.UIModel
import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object AddressesTable : IntIdTable(), CustomTable {
    val address: Column<String> = varchar("address", 100)

    override fun toUI(row: ResultRow) = UIModel(
        listOf(
            "Адрес" to row[address]
        )
    )

    override suspend fun filtered(params: MutableSet<FilterParam<*>>): List<UIModel> {
        return transaction {
            val all = selectAll()
            params.forEach { param ->
                param.like(all)
            }
            return@transaction all.map(::toUI)
        }
    }

    override suspend fun delete(model: UIModel) {
        return transaction {
            deleteWhere(1) {
                address eq model.params.first().second
            }
        }
    }
}
