package data.db.models

data class Relative(
    val name: String,
    val relationDegree: String,
    val employment: String,
    val birthDay: String,
    val birthPlace: String,
    val nationality: String,
    val citizen: String,
    val admissionForm: Int,
)