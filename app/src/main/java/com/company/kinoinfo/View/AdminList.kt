package com.company.kinoinfo.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.company.kinoinfo.Adapters.KinoApadter
import com.company.kinoinfo.Model.Kino
import com.company.kinoinfo.R
import com.company.kinoinfo.ViewModel.AddGetKinoViewModel
import com.company.kinoinfo.databinding.ActivityAdminListBinding
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow


class AdminList : AppCompatActivity() {

    private lateinit var binding: ActivityAdminListBinding
    private lateinit var loadDialog: AlertDialog
    private val addGetKinoViewModel : AddGetKinoViewModel by viewModels()
    private lateinit var adapter: KinoApadter
    private lateinit var listKino:ArrayList<Kino>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listKino=ArrayList<Kino>()
        binding=ActivityAdminListBinding.inflate(layoutInflater)


        binding.addKino.setOnClickListener {
            startActivity(Intent(this, AddKino::class.java))
        }
        loadData()


        adapter= KinoApadter(this,listKino)
        binding.coverFlowAdmin.adapter=adapter
        binding.coverFlowAdmin.setOnScrollPositionListener(object : FeatureCoverFlow.OnScrollPositionListener{
            override fun onScrolledToPosition(position: Int) {
                binding.titleKino.setText(listKino.get(position).name)
            }

            override fun onScrolling() {

            }

        })
        binding.coverFlowAdmin.onItemClickListener=AdapterView.OnItemClickListener{adapterView, view, i, l ->
            val intent=Intent(this, EditActivity::class.java)
            intent.putExtra("item",listKino.get(i))
            startActivity(intent)
        }

    }

    fun loadData(){
        val dialogView=layoutInflater.inflate(R.layout.loading_list,null)
        val builder=AlertDialog.Builder(this)
        builder.setView(dialogView)
        builder.setCancelable(false)
        loadDialog=builder.create()
        loadDialog.show()

        addGetKinoViewModel.getListKinoViewModel()?.observe(this, Observer {

            listKino=it
            adapter.updateAdapter(listKino)




        })
        while(listKino.size<3){
            val kinoTest1= Kino("test","https://static.tildacdn.com/tild3331-3639-4463-b932-616635323361/the-question-mark-20.png","test")
            listKino.add(kinoTest1)

        }
        setupUI()

    }
    fun setupUI(){

        setContentView(binding.root)
        binding.titleKino.setFactory {
           val inflater=LayoutInflater.from(this)
            inflater.inflate(R.layout.layout_title,null) as TextView
        }
        val inAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left)
        val outAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right)

        binding.titleKino.inAnimation=inAnim
        binding.titleKino.outAnimation=outAnim


        val display=windowManager.defaultDisplay
        val outMetrics=DisplayMetrics()

        display.getMetrics(outMetrics)

        val density= resources.displayMetrics.density

        val dbHeight=outMetrics.heightPixels/density
        val dbWidth=outMetrics.widthPixels/density

        binding.coverFlowAdmin.coverHeight=dbHeight.toInt()
        binding.coverFlowAdmin.coverWidth=dbWidth.toInt()

        loadDialog.dismiss()
    }
}