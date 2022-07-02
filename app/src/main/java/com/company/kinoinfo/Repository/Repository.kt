package com.company.kinoinfo.Repository

import com.company.kinoinfo.BussinessLogic.KinoBusLog

class Repository {

    companion object {
        var database: KinoBusLog?=null

        fun initDataBase() {
            if (database == null) {
                database = KinoBusLog()
            }
        }

        fun getDataBase(): KinoBusLog? {
            return database
        }
    }
}