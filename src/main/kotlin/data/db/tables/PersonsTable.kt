package data.db.tables

import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ui.views.UIModel

object PersonsTable : IntIdTable(), CustomTable {
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



    override suspend fun filtered(params: MutableSet<FilterParam<*>>): List<UIModel> {
        return transaction {
            val selected = selectAll()
            params.forEach { param ->
                param.like(selected)
            }
            return@transaction selected.map(::toUI)
        }
    }

    override suspend fun delete(model: UIModel) {
        return transaction {
            deleteWhere(1) {
                name eq model.params.first().second
            }
        }
    }

    override suspend fun edit(oldModel: UIModel, newModel: UIModel) {
        transaction {
            update({
                name eq oldModel.params.first().second
            }) {
                it[name] = newModel.params[0].second
                it[age] = newModel.params[1].second.toInt()
                it[weight] = newModel.params[2].second.toFloat()
                it[address] = newModel.params[3].second.toInt()
            }
        }
    }

    override suspend fun add(uiModel: UIModel) {
        transaction {
            insert {
                it[name] = uiModel.params[0].second
                it[age] = uiModel.params[1].second.toInt()
                it[weight] = uiModel.params[2].second.toFloat()
                it[address] = uiModel.params[3].second.toInt()
            }
        }
    }


}
