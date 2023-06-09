package com.yukaida.notecompose.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val _name = MutableLiveData("originName")
    val name: LiveData<String> get() = _name

    fun changeName(newName: String) {
        _name.postValue(newName)
    }


}