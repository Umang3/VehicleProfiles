package com.example.bizlersassignment

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bizlersassignment.database.CarDataBase
import com.example.bizlersassignment.database.CarEntity

class HomeActivity : AppCompatActivity() {
    lateinit var toolbar6 : Toolbar
    lateinit var recyclerView6: RecyclerView
    lateinit var homeDataAdaptor: HomeDataAdaptor
    lateinit var checkData : Button
    lateinit var goToAdd : Button
    var carInfoList = arrayListOf<CarInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        recyclerView6 = findViewById(R.id.recyclerView6)
        checkData = findViewById(R.id.checkData)
        goToAdd = findViewById(R.id.goToAdd)
        goToAdd.setOnClickListener {
            startActivity(Intent(this@HomeActivity,MainActivity::class.java))
        }
        checkData.setOnClickListener {

            val carList = GetAllFavSyncTask(this@HomeActivity).execute().get()
            if(carList.isEmpty()){
                Toast.makeText(this@HomeActivity,"No Data Found!!",Toast.LENGTH_SHORT).show()
            }else{
            for(i in carList) {
                val carInfo = CarInfo(
                    i.id,
                    i.selectName,
                    i.model,
                    i.petrol,
                    i.transmission
                )
                carInfoList.add(carInfo)
                homeDataAdaptor = HomeDataAdaptor(this, carInfoList)
                recyclerView6.adapter = homeDataAdaptor
                recyclerView6.layoutManager = LinearLayoutManager(this)
                checkData.visibility = View.GONE
                goToAdd.visibility = View.GONE
            }
            }

        }
        setupToolbar()
     //   homeDataAdaptor.notifyDataSetChanged()
    }

    private fun setupToolbar() {
        toolbar6 = findViewById(R.id.toolbar6)
        setSupportActionBar(toolbar6)
        supportActionBar?.title = " Hello Buddy"
    }

    class GetAllFavSyncTask(context : Context) : AsyncTask<Void, Void, List<CarEntity>>() {

        val db = Room.databaseBuilder(context,CarDataBase::class.java,"car-db").build()
        override fun doInBackground(vararg p0: Void?): List<CarEntity> {
            return db.carDao().getAllCars()
        }
    }
}