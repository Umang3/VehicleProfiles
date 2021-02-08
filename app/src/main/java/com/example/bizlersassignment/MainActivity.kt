package com.example.bizlersassignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar : Toolbar
    lateinit var editTextVehicleNumber : EditText
    lateinit var forwardImage : ImageView
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextVehicleNumber = findViewById(R.id.editTextVehicleNumber)
        forwardImage = findViewById(R.id.forwardImage)
        forwardImage.setOnClickListener {
            val editTextVehicleNumber1 = editTextVehicleNumber.text.toString()
            if(editTextVehicleNumber1.isEmpty()){
                Toast.makeText(this@MainActivity,"enter valid text",Toast.LENGTH_SHORT).show()
            }else{
                sharedPreferences = getSharedPreferences(R.string.store_number.toString(), Context.MODE_PRIVATE)

                val intent = Intent(this@MainActivity,SelectMakeActivity::class.java)
                intent.putExtra("number","$editTextVehicleNumber1")
                startActivity(intent)
            }
        }
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = " Create new Vehicle Profile"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}