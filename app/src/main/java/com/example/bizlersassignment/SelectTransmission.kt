package com.example.bizlersassignment

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectTransmission : AppCompatActivity() {
    lateinit var recyclerView4 : RecyclerView
    lateinit var toolbar4 : Toolbar
    var arrayList4 = arrayListOf<String>()
    lateinit var selectTransmissionAdaptor: SelectTransmissionAdaptor
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_transmission)
        recyclerView4 = findViewById(R.id.recyclerView4)
        val str1 = intent.getStringExtra("fuel_type")
        arrayList4.add("Manual")
        arrayList4.add("Automatic")
        selectTransmissionAdaptor = SelectTransmissionAdaptor(this,arrayList4)
        recyclerView4.adapter = selectTransmissionAdaptor
        recyclerView4.layoutManager = LinearLayoutManager(this)
        sharedPreferences = getSharedPreferences(R.string.store_details.toString(), Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("fuel_type",str1).apply()
        selectTransmissionAdaptor.notifyDataSetChanged()
     setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        toolbar4 = findViewById(R.id.toolbar4)
        setSupportActionBar(toolbar4)
        supportActionBar?.title = " Select Model"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}