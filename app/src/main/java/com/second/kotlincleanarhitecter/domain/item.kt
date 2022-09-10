package com.second.kotlincleanarhitecter.domain

data class item(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFIEND_ID
) {
val UNDEFIEND_ID = -1

    companion object {
        const val UNDEFIEND_ID = -1
    }
}
