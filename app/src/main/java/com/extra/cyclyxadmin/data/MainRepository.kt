package com.extra.cyclyxadmin.data

import com.extra.cyclyxadmin.model.ReferenceItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository{
    val database = FirebaseDatabase.getInstance().reference

    fun getItemReference(type : String,uid : String){
        database.child(type).child(uid).addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    p0.getValue(ReferenceItem::class.java)
                }

                override fun onCancelled(p0: DatabaseError) {

                }

            }
        )
    }
}