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

class SelectModelAdaptor(val context: Context , var arrayList1: ArrayList<String>) : RecyclerView.Adapter<SelectModelAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_make_adaptor,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayList2 = arrayList1[position]
        holder.textSelectMakeName1.text = arrayList2
        holder.imageSelectMakeName1.setOnClickListener{
            val intent = Intent(this.context,FuelTypeActivity::class.java)
            intent.putExtra("selection_model","${holder.textSelectMakeName1.text}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList1.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textSelectMakeName1 : TextView = view.findViewById(R.id.textSelectMakeName)
        val imageSelectMakeName1 : ImageView = view.findViewById(R.id.imageSelectMakeName)
    }
}