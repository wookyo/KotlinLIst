package com.v1.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v1.application.model.ResponseMainList
import com.v1.application.model.ResponseMainList.MainListItem
import com.v1.application.repository.MainListRepository


class MainListViewModel : ViewModel() {
    private val repository : MainListRepository = MainListRepository()
    private val _selectItemData = MutableLiveData<MainListItem>()
    private var mainListLiveData = MutableLiveData<ResponseMainList?>()

    val selectItemData get() = _selectItemData

    init {
        mainListLiveData = repository.getMainListLiveData()
    }

    fun updateSelectItem(item: MainListItem){
        _selectItemData.value = item
    }

    fun getSelectItem(): LiveData<MainListItem>{
        return selectItemData
    }

    fun getMainListLiveData(): LiveData<ResponseMainList?>{
        return mainListLiveData
    }

    fun requestMainListData(){
        repository.requestMainListData()
    }


}