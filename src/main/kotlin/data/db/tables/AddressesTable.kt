package data.db.tables

import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ui.views.UIModel

object AddressesTable : IntIdTable(), CustomTable {
    val address: Column<String> = varchar("address", 100)

    override fun toUI(row: ResultRow) = UIModel(
        listOf(
            "Адрес" to row[address]
        )
    )

    override suspend fun add(uiModel: UIModel) {
        transaction {
            insert {
                it[address] = uiModel.params.first().second
            }
        }
    }

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
        transaction {
            deleteWhere(1) {
                address eq model.params.first().second
            }
        }
    }

    override suspend fun edit(oldModel: UIModel, newModel: UIModel) {
        transaction {
            update({
                address eq oldModel.params.first().second
            }) {
                it[address] = newModel.params.first().second
            }
        }
    }
}
