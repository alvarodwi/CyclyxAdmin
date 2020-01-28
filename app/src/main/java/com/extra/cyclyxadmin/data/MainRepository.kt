package com.extra.cyclyxadmin.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.extra.cyclyxadmin.model.ReferenceItem
import com.extra.cyclyxadmin.utils.firebaseConstants.BASE_KEY
import com.extra.cyclyxadmin.utils.firebaseConstants.REFERENSI_KEY
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository{
    val database = FirebaseDatabase.getInstance().reference

    fun getAllReference(type : String) :List<ReferenceItem>{
        val list = ArrayList<ReferenceItem>()
        database.child(BASE_KEY).child(REFERENSI_KEY).child(type).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (list.isNotEmpty()) list.clear()
                for (i in p0.children) {
                    val model = i.getValue(ReferenceItem::class.java)
                    model?.let {
                        list.add(it)
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("ERR", "onCancelled: $p0")
            }
        })
        return list
    }

    fun getItemReference(type : String,uid : String) : ReferenceItem?{
        var item : ReferenceItem ?= null
        database.child(BASE_KEY).child(REFERENSI_KEY).child(type).child(uid).addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    item = p0.getValue(ReferenceItem::class.java)
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.e("ERR", "onCancelled: $p0")
                }
            }
        )
        Log.d("ADD","Item -> $item")
        return item
    }

    fun addReference(model : ReferenceItem){
        val uid = "${database.push().key}"
        model.uid = uid
        database.child(BASE_KEY).child(REFERENSI_KEY).child(model.type).child(uid).setValue(model)
    }

    fun editReference(model : ReferenceItem){
        database.child(BASE_KEY).child(REFERENSI_KEY).child(model.type).child(model.uid).setValue(model)
    }

    fun removeReference(type: String,model : ReferenceItem?){
        model?.let{
            database.child(BASE_KEY).child(REFERENSI_KEY).child(type).child(model.uid).removeValue()
        }
    }
}