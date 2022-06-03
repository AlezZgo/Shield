package data.db.models.params

import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.andWhere

data class IntFilterParam(
    override val name: String,
    override val column: Column<Int>,
    override val param: Int
) : FilterParam<Int> {
    override fun like(query: Query): Query {
        return query.andWhere {
            column.eq(param)
        }
    }

//    override fun delete(query: Query): Query {
//        TODO("Not yet implemented")
//    }

}