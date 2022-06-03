package data.db.models.params

import data.db.models.params.core.FilterParam
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.upperCase

data class StringFilterParam(
    override val name: String,
    override val column: Column<String>,
    override val param: String
) : FilterParam<String> {
    override fun like(query: Query): Query {
        return query.andWhere {
            column.upperCase().like("%${param.uppercase()}%")
        }
    }

//    override fun delete(deleteBlock : ()-> Unit) {
//        transaction {
//            delete {
//
//            }
//        }
//        return
//    }


}