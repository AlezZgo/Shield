package data.db.models

import data.db.models.params.core.Param
import data.db.tables.Persons
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

data class Common(
    val paramList : List<Param<*>>
) : Completable {

    override suspend fun full(): List<Param<*>> {
        val listOfFulledParams = mutableListOf<Param<*>>()
        paramList.forEach { param ->

            if (param.isLink()){
                transaction {
                    Persons.selectAll().andWhere {
                        Persons.name.like("%${requestText.value}%")
                    }.map(Persons::toPerson)
                }
            }else{
                listOfFulledParams.add(param)
            }


        }
        return listOfFulledParams

    }


}