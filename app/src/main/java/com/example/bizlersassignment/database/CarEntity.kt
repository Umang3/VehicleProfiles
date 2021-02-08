package com.example.bizlersassignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carsList")

data class CarEntity (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name") val selectName: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "petrol") val petrol: String,
    @ColumnInfo(name = "transmission") val transmission: String
    )