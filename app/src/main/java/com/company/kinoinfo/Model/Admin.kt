package com.company.kinoinfo.Model

data class Admin(val id_phone:Int?=0,val name:String?="",val pass:String?="") {
    companion object{
        var currentAdmin: Admin?=null
    }
}