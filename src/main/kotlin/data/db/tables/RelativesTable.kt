package data.db.tables

import data.db.models.Relative
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object RelativesTable : IntIdTable() {
    val name: Column<String> = varchar("name", 20)
    val relationDegree: Column<String> = varchar("relationDegree", 30)
    val employment: Column<String> = varchar("employment", 40)
    val birthDay: Column<String> = varchar("birthDay", 20)
    val birthPlace: Column<String> = varchar("birthPlace", 40)
    val nationality: Column<String> = varchar("nationality", 40)
    val citizen: Column<String> = varchar("citizen", 40)
    val admissionForm: Column<Int> = integer("admissionForm")

    fun toRelative(row: ResultRow) = Relative(
        name = row[name],
        relationDegree = row[relationDegree],
        employment = row[employment],
        birthDay = row[birthDay],
        birthPlace = row[birthPlace],
        nationality = row[nationality],
        citizen = row[citizen],
        admissionForm = row[admissionForm],
    )

}