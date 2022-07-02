package com.company.kinoinfo.DataBase

import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseConnect {


    fun databaseConnect(): FirebaseDatabase {

        return FirebaseDatabase.getInstance()
    }
}