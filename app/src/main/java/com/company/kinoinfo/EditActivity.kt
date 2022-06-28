package com.company.kinoinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.company.kinoinfo.databinding.ActivityEditBinding
import com.squareup.picasso.Picasso

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    private val addGetKinoViewModel:AddGetKinoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kino=intent.getSerializableExtra("item") as Kino
        if(intent!=null){


            Picasso.get().load(kino.image).fit().into(binding.editImage)
            binding.editTitle.setText(kino.name)
            binding.editDesc.setText(kino.description)
        }

        binding.update.setOnClickListener {
            val kinoUpdate=Kino(binding.editTitle.text.toString(),kino.image,binding.editDesc.text.toString())
            addGetKinoViewModel.updateKinoViewModel(kinoUpdate)
            startActivity(Intent(this,AdminList::class.java))
        }
        binding.delete.setOnClickListener {
            val kinoRemove=Kino(binding.editTitle.text.toString(),kino.image,binding.editDesc.text.toString())
            addGetKinoViewModel.removeKinoViewModel(kinoRemove)
            startActivity(Intent(this,AdminList::class.java))

        }
    }
}