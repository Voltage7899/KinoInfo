package com.company.kinoinfo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.company.kinoinfo.Model.Kino
import com.company.kinoinfo.Repository.Repository

class AddGetKinoViewModel : ViewModel() {



    fun addKinoViewModel(kino: Kino):Boolean?{
        return Repository.getDataBase()?.addKinoInsert(kino)
    }

    fun getListKinoViewModel():LiveData<ArrayList<Kino>>?{

        return Repository.getDataBase()?.getListKinoSelect()

    }

    fun updateKinoViewModel(kino: Kino):Unit?{
        return Repository.getDataBase()?.updateKinoUpdate(kino)
    }

    fun removeKinoViewModel(kino: Kino):Unit?{
        return Repository.getDataBase()?.deleteKinoUpdate(kino)
    }

}