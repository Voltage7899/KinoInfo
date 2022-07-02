package com.company.kinoinfo.BussinessLogic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.kinoinfo.DataBase.FirebaseDatabaseConnect
import com.company.kinoinfo.Model.Admin
import com.company.kinoinfo.Model.Kino
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class KinoBusLog {

     private var database:FirebaseDatabase?=null

    init {

        database= FirebaseDatabaseConnect().databaseConnect()
    }

    fun regInsert(admin: Admin):Boolean{

        var status=false

        database?.getReference("Admin")?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child(admin.id_phone.toString()).exists()){
                    status=false
                }
                else{
                    database?.getReference("Admin")?.child(admin.id_phone.toString())?.setValue(admin)?.addOnSuccessListener {
                        status=true
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                status=false
            }

        })


        return status
    }

    fun signSelect(id_phone:Int,pass:String): MutableLiveData<Boolean> {



        val status1=MutableLiveData<Boolean>()

        database?.getReference("Admin")?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child(id_phone.toString()).exists()){
                    val admin=snapshot.child(id_phone.toString()).getValue(Admin::class.java)

                    if(id_phone==admin?.id_phone && pass==admin.pass){

                        Admin.currentAdmin=admin
                        status1.value=true

                    }
                    else{
                        status1.value=false

                    }
                }
                else{
                    status1.value=false

                }
            }

            override fun onCancelled(error: DatabaseError) {
                status1.value=false

            }

        })

        return status1
    }

    fun addKinoInsert(kino: Kino):Boolean{

        var status=false

        database?.getReference("Kino")?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child(kino.name.toString()).exists()){
                    status=false
                }
                else{

                    database?.getReference("Kino")?.child(kino.name.toString())?.setValue(kino)?.addOnSuccessListener {
                        status=true
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                status=false
            }

        })
        return status
    }

    fun getListKinoSelect(): LiveData<ArrayList<Kino>> {
        val mutableLiveDataKinoList=MutableLiveData<ArrayList<Kino>>()


        database?.getReference("Kino")?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val commonList=ArrayList<Kino>()
                for (kino in snapshot.children){
                    var kinoModel=kino.getValue(Kino::class.java)
                    if (kinoModel != null) {
                        commonList.add(kinoModel)
                    }
                }
                mutableLiveDataKinoList.value=commonList
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



        return mutableLiveDataKinoList
    }
    fun updateKinoUpdate(kino: Kino){

        database?.getReference("Kino")?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                database?.getReference("Kino")?.child(kino.name.toString())?.setValue(kino)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun deleteKinoUpdate(kino: Kino){
        database?.getReference("Kino")?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                database?.getReference("Kino")?.child(kino.name.toString())?.removeValue()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}