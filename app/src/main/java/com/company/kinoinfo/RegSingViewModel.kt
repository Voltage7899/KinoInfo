package com.company.kinoinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegSingViewModel :ViewModel(){

    fun regInsertViewModel(admin: Admin):Boolean?{
        return Repository.getDataBase()?.regInsert(admin)
    }
    fun signSelectViewModel(id_phone:Int,pass:String): LiveData<Boolean>? {
        return Repository.getDataBase()?.signSelect(id_phone,pass)
    }
}