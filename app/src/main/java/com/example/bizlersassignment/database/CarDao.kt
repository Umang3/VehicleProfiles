package com.example.bizlersassignment.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Insert
    fun insertCars(restaurantEntity: CarEntity)
    @Delete
    fun deleteCars(restaurantEntity: CarEntity)

    @Query("SELECT * FROM carsList")
    fun getAllCars() :List<CarEntity>
    @Query("SELECT * FROM carsList WHERE id =:carId")
    fun getCarById(carId : String) :CarEntity

}