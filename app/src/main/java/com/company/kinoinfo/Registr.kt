package com.company.kinoinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.company.kinoinfo.databinding.ActivityRegSignBinding
import com.company.kinoinfo.databinding.ActivityRegistrBinding

class Registr : AppCompatActivity() {

    lateinit var binding: ActivityRegistrBinding
    private val regSingViewModel : RegSingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regReg.setOnClickListener {

            val name = binding.regName.text.toString()
            val pass = binding.regPass.text.toString()

            try {

                val phone =binding.regPhone.text.toString().toInt()
                val code = binding.regCode.text.toString().toInt()

                if (  code!=123){
                    Toast.makeText(this,"Проверьете код админа", Toast.LENGTH_SHORT).show()

                }
                else{
                    val admin=Admin(phone,name,pass)
                    regSingViewModel.regInsertViewModel(admin)
                    startActivity(Intent(this, RegSign::class.java))
                }
            }
            catch (ex: Exception){
                Toast.makeText(this,"Телефон и код цифрами!!", Toast.LENGTH_SHORT).show()
            }



        }
    }
}