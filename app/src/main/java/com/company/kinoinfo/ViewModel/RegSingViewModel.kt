package com.company.kinoinfo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.company.kinoinfo.Model.Admin
import com.company.kinoinfo.Repository.Repository

class RegSingViewModel :ViewModel(){

    fun regInsertViewModel(admin: Admin):Boolean?{
        return Repository.getDataBase()?.regInsert(admin)
    }
    fun signSelectViewModel(id_phone:Int,pass:String): LiveData<Boolean>? {
        return Repository.getDataBase()?.signSelect(id_phone,pass)
    }
}