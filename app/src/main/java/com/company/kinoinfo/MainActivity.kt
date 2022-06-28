package com.company.kinoinfo

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
import com.company.kinoinfo.databinding.ActivityMainBinding
import com.company.kinoinfo.databinding.ActivityRegSignBinding
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var loadDialog: AlertDialog
    private val addGetKinoViewModel : AddGetKinoViewModel by viewModels()
    private lateinit var adapter:KinoApadter
    private lateinit var listKino:ArrayList<Kino>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listKino=ArrayList<Kino>()
        binding.bottomMenu.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.admin ->{
                    val admin = Intent(this, RegSign::class.java)
                    startActivity(admin)
                }
            }

            true
        }
        Repository.initDataBase()


        loadData()


        adapter= KinoApadter(this,listKino)
        binding.coverFlowUser.adapter=adapter
        binding.coverFlowUser.setOnScrollPositionListener(object : FeatureCoverFlow.OnScrollPositionListener{
            override fun onScrolledToPosition(position: Int) {
                binding.titleKinoUser.setText(listKino.get(position).name)
            }

            override fun onScrolling() {

            }

        })
        binding.coverFlowUser.onItemClickListener=
            AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val intent=Intent(this,EditActivity::class.java)
            intent.putExtra("item",listKino.get(i))
            startActivity(intent)
        }

    }
    fun setupUI(){

        setContentView(binding.root)
        binding.titleKinoUser.setFactory {
            val inflater= LayoutInflater.from(this)
            inflater.inflate(R.layout.layout_title,null) as TextView
        }
        val inAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left)
        val outAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right)

        binding.titleKinoUser.inAnimation=inAnim
        binding.titleKinoUser.outAnimation=outAnim


        val display=windowManager.defaultDisplay
        val outMetrics= DisplayMetrics()

        display.getMetrics(outMetrics)

        val density= resources.displayMetrics.density

        val dbHeight=outMetrics.heightPixels/density
        val dbWidth=outMetrics.widthPixels/density

        binding.coverFlowUser.coverHeight=dbHeight.toInt()
        binding.coverFlowUser.coverWidth=dbWidth.toInt()

        loadDialog.dismiss()
    }
    fun loadData(){
        val dialogView=layoutInflater.inflate(R.layout.loading_list,null)
        val builder= AlertDialog.Builder(this)
        builder.setView(dialogView)
        builder.setCancelable(false)
        loadDialog=builder.create()
        loadDialog.show()

        addGetKinoViewModel.getListKinoViewModel()?.observe(this, Observer {

            listKino=it
            adapter.updateAdapter(listKino)




        })
        while(listKino.size<3){
            val kinoTest1=Kino("test","https://static.tildacdn.com/tild3331-3639-4463-b932-616635323361/the-question-mark-20.png","test")
            listKino.add(kinoTest1)

        }
        setupUI()

    }

}