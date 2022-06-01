package data.db.tables

import data.db.models.Relative
import data.db.models.UIModel
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object RelativesTable : IntIdTable(),UITable {
    val name: Column<String> = varchar("name", 20)
    val relationDegree: Column<String> = varchar("relationDegree", 30)
    val employment: Column<String> = varchar("employment", 40)
    val birthDay: Column<String> = varchar("birthDay", 20)
    val birthPlace: Column<String> = varchar("birthPlace", 100)
    val birthCountry: Column<String> = varchar("birthCountry", 40)
    val nationality: Column<String> = varchar("nationality", 40)
    val citizen: Column<String> = varchar("citizen", 40)
    val admissionForm: Column<Int> = integer("admissionForm")

    override fun toUI(row: ResultRow) = UIModel(listOf(
        "ФИО:" to row[name],
        "Степень родства:" to row[relationDegree],
        "Работа/учёба:" to row[employment],
        "День рождения:" to row[birthDay],
        "Место рождения:" to row[birthPlace],
        "Страна рождения:" to row[birthCountry],
        "Национальность:" to row[nationality],
        "Гражданство:" to row[citizen],
        "Форма допуска:" to row[admissionForm].toString(),
    ))

}