package data.db.models.params

import data.db.models.params.core.Param

data class LinksParam(
    override val name: String,
    override val params: List<Int>
) : Param<Int> {
    override fun isLink(): Boolean = true
}