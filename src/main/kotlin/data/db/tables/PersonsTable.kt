package data.db.tables

import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ui.views.UIModel

object PersonsTable : IntIdTable(), CustomTable {
    val name:       Column<String> = varchar("name", 100)
    val obj:         Column<String> = varchar("object", 100)
    val jobPosition: Column<String> = varchar("jobPosition", 100)
    val militaryRank: Column<String> = varchar("militaryRank", 100)
    val sex:         Column<String> = varchar("sex", 100)
    val maidenName:  Column<String> = varchar("maidenName", 100)
    val birthDay:    Column<String> = varchar("birthDay",100)
    val birthPlace:  Column<String> = varchar("birthPlace", 100)
    val birthCountry: Column<String> = varchar("birthCountry", 100)
    val nationality: Column<String> = varchar("nationality", 100)
    val citizen:     Column<String> = varchar("citizen", 100)
    val admissionForm: Column<Int> = integer("admissionForm")

    override fun toUI(row: ResultRow) = UIModel(
        listOf(
            "ФИО" to row[name],
            "Объект" to row[jobPosition].toString(),
            "Должность" to row[obj].toString(),
            "Воинское звание" to row[militaryRank].toString(),
            "Пол" to row[sex].toString(),
            "Девичья фамилия" to row[maidenName].toString(),
            "День рождения" to row[birthDay].toString(),
            "Место рождения" to row[birthPlace].toString(),
            "Страна рождения" to row[birthCountry].toString(),
            "Национальность" to row[nationality].toString(),
            "Гражданство" to row[citizen].toString(),
            "Форма допуска" to row[admissionForm].toString(),
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
                it[obj] = newModel.params[1].second
                it[jobPosition] = newModel.params[2].second
                it[militaryRank] = newModel.params[3].second
                it[sex] = newModel.params[4].second
                it[maidenName] = newModel.params[5].second
                it[birthDay] = newModel.params[6].second
                it[birthPlace] = newModel.params[7].second
                it[birthCountry] = newModel.params[8].second
                it[nationality] = newModel.params[9].second
                it[citizen] = newModel.params[10].second
                it[admissionForm] = newModel.params[10].second.toInt()
            }
        }
    }

    override suspend fun add(uiModel: UIModel) {
        transaction {
            insert {
                it[name] = uiModel.params[0].second
                it[obj] = uiModel.params[1].second
                it[jobPosition] = uiModel.params[2].second
                it[militaryRank] = uiModel.params[3].second
                it[sex] = uiModel.params[4].second
                it[maidenName] = uiModel.params[5].second
                it[birthDay] = uiModel.params[6].second
                it[birthPlace] = uiModel.params[7].second
                it[birthCountry] = uiModel.params[8].second
                it[nationality] = uiModel.params[9].second
                it[citizen] = uiModel.params[10].second
                it[admissionForm] = uiModel.params[11].second.toInt()
            }
        }
    }


}
