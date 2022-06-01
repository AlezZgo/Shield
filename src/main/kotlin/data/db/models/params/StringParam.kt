package data.db.models.params

import data.db.models.params.core.Param

data class StringParam(
    override val name: String,
    override val params: List<String>
) : Param<String> {
    override fun isLink(): Boolean = false
}