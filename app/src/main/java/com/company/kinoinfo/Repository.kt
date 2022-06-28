package com.company.kinoinfo

class Repository {

    companion object {
        var database: KinoRepository?=null

        fun initDataBase() {
            if (database == null) {
                database = KinoRepository()
            }
        }

        fun getDataBase(): KinoRepository? {
            return database
        }
    }
}