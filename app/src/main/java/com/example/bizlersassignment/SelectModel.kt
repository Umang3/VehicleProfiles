package com.example.bizlersassignment

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.bizlersassignment.SelectMakeActivity.Companion.b2
import com.example.bizlersassignment.SelectMakeActivity.Companion.b4
import org.json.JSONArray

class SelectModel : AppCompatActivity() {
    lateinit var toolbar2: Toolbar
    lateinit var recyclerView2: RecyclerView
    var finalUrl : String = ""
    val arrayList1 = arrayListOf<String>()
    lateinit var selectModelAdaptor: SelectModelAdaptor
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_model)
        recyclerView2 = findViewById(R.id.recyclerView2)
        toolbar2 = findViewById(R.id.toolbar2)
        val str = intent.getStringExtra("selection_type")
        Toast.makeText(this@SelectModel,"$str",Toast.LENGTH_LONG).show()
        val url1 = "https://test.turbocare.app/turbo/care/v1/models?"
        if(b2){
            val url2 = "class=2w&make="
            val url3 = str
             finalUrl = url1+url2+url3
        }else if(b4){
            val url2 = "class=4w&make="
            val url3 = str
             finalUrl = url1+url2+url3
        }else{
            Toast.makeText(this@SelectModel,"error found",Toast.LENGTH_LONG).show()
            super.onBackPressed()
        }
        sharedPreferences = getSharedPreferences(R.string.store_details.toString(), Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("selection_type",str).apply()
        getData()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar2 = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar2)
        supportActionBar?.title = " Select Model"
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

    private fun getData(){
        try {
            val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, finalUrl, null, { response ->
                val jsnArray = JSONArray(response.toString())
                for (i in 0 until jsnArray.length()) {
                    arrayList1.add(jsnArray.getString(i))
                }
                selectModelAdaptor = SelectModelAdaptor(this, arrayList1)
                recyclerView2.adapter = selectModelAdaptor
                recyclerView2.layoutManager = LinearLayoutManager(this)
            }, { error ->
                Toast.makeText(this@SelectModel, error.message, Toast.LENGTH_LONG).show()
            })
            Volley.newRequestQueue(this).add(jsonObjectRequest)
            selectModelAdaptor.notifyDataSetChanged()
        }catch(e : Exception){
            e.printStackTrace()
        }
    }
}