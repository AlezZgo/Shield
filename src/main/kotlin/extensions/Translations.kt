package extensions

fun String.toRussian() : String = when(this){
    "name" -> "Имя"
    "relationDegree" -> "Степень родства"
    "employment" -> "Место работы (учебы)"
    "birthDay" -> "День рождения"
    "birthPlace" -> "Место рождения"
    "birthCountry" -> "Страна рождения"
    "nationality" -> "Национальность"
    "citizen" -> "Гражданство"
    "admissionForm" -> "Форма допуска"
    "age" -> "Возраст"
    "object" -> "Объект"
    "jobPosition" -> "Должность"
    "militaryRank" -> "Воинское звание"
    "sex" -> "Пол"
    "maidenName" -> "Девичья фамилия"
    "weight" -> "Вес"
    "address","address_id" -> "Адрес"

    "Addresses" -> "Адреса"
    "Relatives" -> "Родственники"
    "Persons" -> "Лица"
    else -> {this}
}