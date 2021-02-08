package com.example.bizlersassignment

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FuelTypeActivity : AppCompatActivity() {
    lateinit var recyclerView3 : RecyclerView
    lateinit var fuelAdaptor: FuelAdaptor
    lateinit var toolbar3 : Toolbar
     var arrayList3 = arrayListOf<String>()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_type)
        recyclerView3 = findViewById(R.id.recyclerView3)
        toolbar3 = findViewById(R.id.toolbar3)
        val str1 = intent.getStringExtra("selection_model")
        arrayList3.add("Petrol")
        arrayList3.add("Diesel")
        arrayList3.add("CNG")
        arrayList3.add("Petrol + CNG")
        arrayList3.add("Electric")
        arrayList3.add("Hybrid")
        fuelAdaptor = FuelAdaptor(this,arrayList3)
        recyclerView3.adapter = fuelAdaptor
        recyclerView3.layoutManager = LinearLayoutManager(this)
        fuelAdaptor.notifyDataSetChanged()
        sharedPreferences = getSharedPreferences(R.string.store_details.toString(), Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("selection_model",str1).apply()
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
        toolbar3 = findViewById(R.id.toolbar3)
        setSupportActionBar(toolbar3)
        supportActionBar?.title = " Select Fuel Type"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}