package extensions

import androidx.compose.ui.text.input.KeyboardType
import data.db.models.params.FloatFilterParam
import data.db.models.params.IntFilterParam
import data.db.models.params.StringFilterParam
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.FloatColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.StringColumnType

fun Column<*>.asFilterParam(valueParam: String) = when (this.columnType) {
    is StringColumnType -> StringFilterParam(this.name, this as Column<String>, valueParam)
    is IntegerColumnType -> IntFilterParam(this.name, this as Column<Int>, valueParam.toInt())
    is FloatColumnType -> FloatFilterParam(this.name, this as Column<Float>, valueParam.toFloat())
    else -> throw RuntimeException("Неверный тип данных")
}

