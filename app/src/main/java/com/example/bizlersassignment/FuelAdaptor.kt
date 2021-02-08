package com.example.bizlersassignment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FuelAdaptor(val context: Context , val arrayList: ArrayList<String>) : RecyclerView.Adapter<FuelAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_make_adaptor,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aryList = arrayList[position]
        holder.textSelectMakeName2.text = aryList
        holder.imageSelectMakeName2.setOnClickListener {
            val intent = Intent(this.context,SelectTransmission::class.java)
            intent.putExtra("fuel_type","${holder.textSelectMakeName2.text}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textSelectMakeName2 : TextView = view.findViewById(R.id.textSelectMakeName)
        val imageSelectMakeName2 : ImageView = view.findViewById(R.id.imageSelectMakeName)
    }
}