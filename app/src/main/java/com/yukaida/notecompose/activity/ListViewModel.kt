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

    //----------------------
    private val _intList = MutableLiveData<MutableList<Int>>(mutableListOf(0))
    val intList: LiveData<MutableList<Int>> get() = _intList
    private val newList = mutableListOf<Int>()
    fun refreshNameList() {
        _intList.value?.let {
            newList.clear()
            repeat(100) {
                newList.add((0..100).random())
            }
            _intList.value = newList
        }

    }

}