package data.db.models.params

import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.andWhere

data class FloatFilterParam(
    override val name: String,
    override val column: Column<Float>,
    override val param: Float
) : FilterParam<Float>{

    override fun like(query: Query): Query {
        return query.andWhere {
            column.eq(param)
        }
    }

}