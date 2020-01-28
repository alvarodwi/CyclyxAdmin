package com.extra.cyclyxadmin.ui.references

import android.app.Application
import androidx.lifecycle.*
import com.extra.cyclyxadmin.data.MainRepository
import com.extra.cyclyxadmin.model.ReferenceItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class BaseReferencesVM(val type: String, val app: Application) : AndroidViewModel(app) {
    private val repo = MainRepository()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _referenceList = MutableLiveData<List<ReferenceItem>>()
    val referenceItem : LiveData<List<ReferenceItem>>
        get() = _referenceList

    init {
       onRefresh()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onRefresh(){
        _referenceList.value = repo.getAllReference(type)
    }

    fun onDeleteItem(uid : String?){
        uid?.let{
            val item = repo.getItemReference(type,uid)
            repo.removeReference(type,item)
        }
    }

    class Factory(val type: String, val app: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BaseReferencesVM::class.java)) {
                return BaseReferencesVM(
                    type,
                    app
                ) as T
            }
            throw IllegalArgumentException("Unknown View Model!")
        }
    }
}