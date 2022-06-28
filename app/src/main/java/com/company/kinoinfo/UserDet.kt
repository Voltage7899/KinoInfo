package com.company.kinoinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.company.kinoinfo.databinding.ActivityEditBinding
import com.company.kinoinfo.databinding.ActivityUserDetBinding
import com.squareup.picasso.Picasso

class UserDet : AppCompatActivity() {
    lateinit var binding: ActivityUserDetBinding
    private val addGetKinoViewModel:AddGetKinoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kino=intent.getSerializableExtra("item") as Kino
        if(intent!=null){


            Picasso.get().load(kino.image).fit().into(binding.editImageUser)
            binding.editTitleUser.setText(kino.name)
            binding.editDescUser.setText(kino.description)
        }
    }
}