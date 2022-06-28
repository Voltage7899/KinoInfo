package com.company.kinoinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.company.kinoinfo.databinding.ActivityAddKinoBinding
import com.squareup.picasso.Picasso

class AddKino : AppCompatActivity() {

    lateinit var binding: ActivityAddKinoBinding
    private val addGetKinoViewModel : AddGetKinoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding=ActivityAddKinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get().load(R.drawable.question).fit().placeholder(R.drawable.question).into(binding.addImage)



        binding.addAdd.setOnClickListener {

            val kinoModel=Kino()
            kinoModel.name=binding.addName.text.toString()
            kinoModel.description=binding.addDesc.text.toString()
            kinoModel.image=binding.addLink.text.toString()


            try {

                addGetKinoViewModel.addKinoViewModel(kinoModel)
                startActivity(Intent(this, AdminList::class.java))
            }
            catch (ex:Exception){
                Toast.makeText(this,"Цена цифрами!!", Toast.LENGTH_SHORT).show()
            }



        }
        binding.addImage.setOnClickListener{

            try{
                Picasso.get().load(binding.addLink.text.toString()).fit().placeholder(R.drawable.question).into(binding.addImage)
            }
            catch (ex:Exception){
                Toast.makeText(this,"Нет ссылки на картинку", Toast.LENGTH_SHORT).show()
            }




        }
    }
}