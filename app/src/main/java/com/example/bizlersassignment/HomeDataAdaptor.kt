package com.example.bizlersassignment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeDataAdaptor(val context: Context , val arrayList: ArrayList<CarInfo>) : RecyclerView.Adapter<HomeDataAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_data_adaptor,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aryList = arrayList[position]
        holder.textNumber.text = aryList.id.toString()
        holder.imageHomeForward.setOnClickListener{
            val intent = Intent(this.context,FinalActivity::class.java)
            this.context.startActivity(intent)
            val a1 = aryList.selectName
            val a2 = aryList.model
            holder.textModelType.text = "${a1}${a2}"
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNumber : TextView = view.findViewById(R.id.textNumber)
        val textModelType : TextView = view.findViewById(R.id.textModelType)
        val imageHomeForward : ImageView = view.findViewById(R.id.imageHomeForward)
    }
}