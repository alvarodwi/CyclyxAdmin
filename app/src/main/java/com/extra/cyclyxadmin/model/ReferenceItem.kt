package com.extra.cyclyxadmin.model

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReferenceItem(
    var uid : String = "",
    var title : String = "",
    var content : String = "",
    var type : String = ""
) : Parcelable{
    @Exclude
    fun toMap() : Map<String,Any?>{
        return mapOf(
            "uid" to uid,
            "title" to title,
            "content" to content
        )
    }
}