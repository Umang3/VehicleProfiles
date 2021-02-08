package com.example.bizlersassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class],version = 1)

abstract class CarDataBase : RoomDatabase() {
    abstract fun carDao() : CarDao

}