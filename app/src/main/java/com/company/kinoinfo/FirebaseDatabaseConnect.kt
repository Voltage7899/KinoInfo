package com.company.kinoinfo

import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseConnect {


    fun databaseConnect(): FirebaseDatabase {

        return FirebaseDatabase.getInstance()
    }
}