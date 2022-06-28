package com.company.kinoinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.company.kinoinfo.databinding.ActivityMainBinding
import com.company.kinoinfo.databinding.ActivityRegSignBinding

class RegSign : AppCompatActivity() {

    lateinit var binding: ActivityRegSignBinding
    private val regSingViewModel : RegSingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signRegBottomMenu.selectedItemId = R.id.admin;

        binding.signRegBottomMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.user -> {
                    val admin = Intent(this, MainActivity::class.java)
                    startActivity(admin)
                }

            }

            true
        }
        binding.register.setOnClickListener {
            startActivity(Intent(this,Registr::class.java))
        }
        binding.sing.setOnClickListener {
            val pass=binding.password.text.toString()
            try{
                val id_phone=binding.phone.text.toString().toInt()

                regSingViewModel.signSelectViewModel(id_phone,pass)?.observe(this, Observer {
                    if(it){

                        Toast.makeText(this,"Добро пожаловать!",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,AdminList::class.java))

                    }
                    else{
                        Toast.makeText(this,"Неверные данные",Toast.LENGTH_SHORT).show()

                    }
                })

            }catch (ex:Exception){
                Toast.makeText(this,"Телефон цифрами!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}