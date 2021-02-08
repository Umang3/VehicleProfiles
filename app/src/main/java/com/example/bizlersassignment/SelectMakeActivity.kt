package com.example.bizlersassignment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.OneShotPreDrawListener.add
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class SelectMakeActivity : AppCompatActivity() {
    companion object{
        var b2 = false
        var b4 = false
    }
    lateinit var recyclerView: RecyclerView
    lateinit var selectMakeAdaptor: SelectMakeAdaptor
    lateinit var toolbar1:Toolbar
    lateinit var twoWheeler : Button
    lateinit var fourWheeler : Button
    var url : String = ""
    val arrayList = arrayListOf<String>()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_make)
        recyclerView = findViewById(R.id.recyclerView)
        val number = intent.getStringExtra("number")
        twoWheeler = findViewById(R.id.twoWheeler)
        fourWheeler = findViewById(R.id.fourWheeler)
        twoWheeler.setOnClickListener {
            b2=true
            b4=false
             url = "https://test.turbocare.app/turbo/care/v1/makes?class=2w"
            getData()
        }
        fourWheeler.setOnClickListener {
            b4=true
            b2=false
             url = "https://test.turbocare.app/turbo/care/v1/makes?class=4w"
            getData()
        }
        sharedPreferences = getSharedPreferences(R.string.store_details.toString(), Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("number",number).apply()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar1 = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        supportActionBar?.title = " Select Make"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getData(){
        try {
            val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->
                val jsnArray = JSONArray(response.toString())
                twoWheeler.visibility = View.GONE
                fourWheeler.visibility = View.GONE
                for (i in 0 until jsnArray.length()) {
                    arrayList.add(jsnArray.getString(i))
                }
                selectMakeAdaptor = SelectMakeAdaptor(this, arrayList)
                recyclerView.adapter = selectMakeAdaptor
                recyclerView.layoutManager = LinearLayoutManager(this)
            }, { error ->
                Toast.makeText(this@SelectMakeActivity, error.message, Toast.LENGTH_LONG).show()
            })
            Volley.newRequestQueue(this).add(jsonObjectRequest)
            selectMakeAdaptor.notifyDataSetChanged()
        }catch(e : Exception){
            e.printStackTrace()
        }
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