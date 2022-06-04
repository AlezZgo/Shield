package data.db.tables

import ui.views.UIModel
import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object RelativesTable : IntIdTable(), CustomTable {
    val name: Column<String> = varchar("name", 20)
    val relationDegree: Column<String> = varchar("relationDegree", 30)
    val employment: Column<String> = varchar("employment", 40)
    val birthDay: Column<String> = varchar("birthDay", 20)
    val birthPlace: Column<String> = varchar("birthPlace", 100)
    val birthCountry: Column<String> = varchar("birthCountry", 40)
    val nationality: Column<String> = varchar("nationality", 40)
    val citizen: Column<String> = varchar("citizen", 40)
    val admissionForm: Column<Int> = integer("admissionForm")

    override fun toUI(row: ResultRow) = UIModel(
        listOf(
            "ФИО:" to row[name],
            "Степень родства:" to row[relationDegree],
            "Работа/учёба:" to row[employment],
            "День рождения:" to row[birthDay],
            "Место рождения:" to row[birthPlace],
            "Страна рождения:" to row[birthCountry],
            "Национальность:" to row[nationality],
            "Гражданство:" to row[citizen],
            "Форма допуска:" to row[admissionForm].toString(),
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
                it[relationDegree] = newModel.params[1].second
                it[employment] = newModel.params[2].second
                it[birthDay] = newModel.params[3].second.toString()
                it[birthPlace] = newModel.params[4].second
                it[birthCountry] = newModel.params[5].second
                it[nationality] = newModel.params[6].second
                it[citizen] = newModel.params[7].second
                it[admissionForm] = newModel.params[8].second.toInt()
            }
        }
    }


    override suspend fun add(uiModel: UIModel) {
        transaction {
            insert {
                it[name] = uiModel.params[0].second
                it[relationDegree] = uiModel.params[1].second
                it[employment] = uiModel.params[2].second
                it[birthDay] = uiModel.params[3].second.toString()
                it[birthPlace] = uiModel.params[4].second
                it[birthCountry] = uiModel.params[5].second
                it[nationality] = uiModel.params[6].second
                it[citizen] = uiModel.params[7].second
                it[admissionForm] = uiModel.params[8].second.toInt()
            }
        }
    }


}