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


class SelectMakeAdaptor(val context: Context , val arrayList: ArrayList<String>) : RecyclerView.Adapter<SelectMakeAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_make_adaptor,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aryList = arrayList[position]
        holder.textSelectMakeName.text = aryList
        holder.imageSelectMakeName.setOnClickListener{
            Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context,SelectModel::class.java)
            intent.putExtra("selection_type","${holder.textSelectMakeName.text}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textSelectMakeName : TextView = view.findViewById(R.id.textSelectMakeName)
        val imageSelectMakeName : ImageView = view.findViewById(R.id.imageSelectMakeName)
    }


}