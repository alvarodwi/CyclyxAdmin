package com.extra.cyclyxadmin.model

import com.google.firebase.database.Exclude

data class ReferenceItem(
    var uid : String = "",
    var title : String = "",
    var content : String = "",
    var type : String = ""
){
    @Exclude
    fun toMap() : Map<String,Any?>{
        return mapOf(
            "uid" to uid,
            "title" to title,
            "content" to content
        )
    }
}