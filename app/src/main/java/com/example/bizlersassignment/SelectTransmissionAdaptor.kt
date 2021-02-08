package com.example.bizlersassignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SelectTransmissionAdaptor(val context: Context , val arrayList: ArrayList<String>) : RecyclerView.Adapter<SelectTransmissionAdaptor.ViewHolder>() {
    lateinit var  sharedPreferences : SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_make_adaptor,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aryList = arrayList[position]
        holder.textSelectMakeName4.text = aryList
        val name = holder.textSelectMakeName4.text.toString()
       sharedPreferences = this.context.getSharedPreferences(R.string.store_details.toString(), Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("select_transmission",name).apply()
        holder.imageSelectMakeName4.setOnClickListener{
            Toast.makeText(context,"Data Saved", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context,FinalActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textSelectMakeName4 : TextView = view.findViewById(R.id.textSelectMakeName)
        val imageSelectMakeName4 : ImageView = view.findViewById(R.id.imageSelectMakeName)
    }
}