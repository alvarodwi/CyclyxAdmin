package com.extra.cyclyxadmin.model

import com.google.firebase.database.Exclude

data class ReferenceItem(
    val uid : String? = "",
    val content : String? = "",
    val type : String? = ""
){
    @Exclude
    fun toMap() : Map<String,Any?>{
        return mapOf(
            "uid" to uid,
            "content" to content,
            "type" to type
        )
    }
}