package data.db.models

data class Person(
    val id: Int = -1,
    val name: String,
    val age: Int,
    val weight: Float,
    val address: Int
) : ListOfStringPairs {
    override fun toListOfStringPairs(): List<Pair<String, String>> =
        listOf(
            "Имя:" to name,
            "Возраст:" to age.toString(),
            "Вес:" to weight.toString(),
            "Адрес:" to address.toString(),

        )

}