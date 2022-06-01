package data.db.models.params.core

interface Param<T> : Link{
    val name : String
    val params : List<T>
}
