package com.company.kinoinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class KinoApadter(private val context: Context,private var kinoList:ArrayList<Kino>) :BaseAdapter(){
    override fun getCount(): Int {
        return kinoList.size
    }

    override fun getItem(p0: Int): Any {
        return kinoList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var rowView:View?=null
        if(rowView==null){
            rowView=LayoutInflater.from(context).inflate(R.layout.element,null)

            val name = rowView!!.findViewById<View>(R.id.element_name) as TextView
            val image = rowView.findViewById<View>(R.id.element_image) as ImageView

            name.text=kinoList.get(position).name

            Picasso.get().load(kinoList.get(position).image.toString()).into(image)
        }
        return rowView
    }
    fun updateAdapter(kinoListNew: ArrayList<Kino>){
        kinoList=kinoListNew
        notifyDataSetChanged()
    }
}