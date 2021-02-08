package com.example.bizlersassignment

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.bizlersassignment.database.CarDataBase
import com.example.bizlersassignment.database.CarEntity
import org.w3c.dom.Text

class FinalActivity : AppCompatActivity() {
    lateinit var textFinalNumber : TextView
    lateinit var textSelectName : TextView
    lateinit var textModelName : TextView
    lateinit var textPetrol : TextView
    lateinit var transmission : TextView
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        textFinalNumber = findViewById(R.id.textFinalNumber)
        textSelectName = findViewById(R.id.textSelectName)
        textModelName = findViewById(R.id.textModelName)
        textPetrol = findViewById(R.id.textPetrol)
        transmission = findViewById(R.id.transmission)
        sharedPreferences = getSharedPreferences(R.string.store_details.toString(), Context.MODE_PRIVATE)
        textFinalNumber.text = sharedPreferences.getString("number",null)
        textSelectName.text = sharedPreferences.getString("selection_type",null)
        textModelName.text = sharedPreferences.getString("selection_model" , null)
        textPetrol.text =  sharedPreferences.getString("fuel_type" , null)
        transmission.text = sharedPreferences.getString("select_transmission" , null)
        val number = textFinalNumber.text.toString()
        val selectName = textSelectName.text.toString()
        val model = textModelName.text.toString()
        val petrol = textPetrol.text.toString()
        val transmission = transmission.text.toString()

        val carEntity = CarEntity(
           number,
            selectName,
            model,
            petrol,
            transmission,
        )
        if (!DBAsyncTask(
                this,
                carEntity,
                1
            ).execute().get()) {
            val async =
                DBAsyncTask(
                    this,
                    carEntity,
                    2
                ).execute()
            val result = async.get()
            if (result) {
                Toast.makeText(this@FinalActivity,"stored",Toast.LENGTH_LONG).show()
            }
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

    class DBAsyncTask(context: Context, val carEntity: CarEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        val db = Room.databaseBuilder(context, CarDataBase::class.java, "car-db").build()

        override fun doInBackground(vararg params: Void?): Boolean {
            when (mode) {
                1 -> {
                    val res: CarEntity? =
                        db.carDao().getCarById(carEntity.id.toString())
                    db.close()
                    return res != null
                }
                2 -> {
                    db.carDao().insertCars(carEntity)
                    db.close()
                    return true
                }
                3 -> {
                    db.carDao().deleteCars(carEntity)
                    db.close()
                    return true
                }
            }
            return false
        }
    }
}