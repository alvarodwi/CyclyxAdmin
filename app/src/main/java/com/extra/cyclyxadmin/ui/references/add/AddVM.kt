package com.extra.cyclyxadmin.ui.references.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.extra.cyclyxadmin.data.MainRepository
import com.extra.cyclyxadmin.model.ReferenceItem
import com.extra.cyclyxadmin.ui.references.BaseReferencesVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddVM(val type: String, private val uid: String?, val app: Application) : AndroidViewModel(app) {
    private val repo = MainRepository()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _item = MutableLiveData<ReferenceItem>()
    val item : LiveData<ReferenceItem>
        get() = _item

    val pageTitle = if(uid == null){
        "Add"
    }else{
        "Edit"
    }

    val btnText = if(uid == null){
        "Submit"
    }else{
        "Update"
    }

    fun onBtnClicked(model : ReferenceItem){
        if (uid == null){
            repo.addReference(model)
            Log.d("ADD","Add Reference")
        }else{
            repo.editReference(model)
            Log.d("ADD","Edit Reference")
        }
    }


    init {
        Log.d("ADD","UID -> $uid")
        if(uid != null){
            _item.value = repo.getItemReference(type,uid)
        }
        Log.d("ADD","Item -> ${_item.value}")
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    class Factory(val type: String, val uid: String?, val app: Application) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddVM::class.java)) {
                return AddVM(
                    type,
                    uid,
                    app
                ) as T
            }
            throw IllegalArgumentException("Unknown View Model!")
        }
    }
}