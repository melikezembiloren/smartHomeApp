package com.example.awoxapp.Repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "savedDevices")
data class Devices(
    @PrimaryKey(autoGenerate = true)
    //@PrimaryKey
    @ColumnInfo(name = "saved_device_ID")
    val idOfSavedDevice: Int = 0,//since it is unique it is kept as primary key with @PrimaryKey annotation and it automatically increased  with "autoGenerate = true"

    @ColumnInfo(name = "saved_device_type")
    val typeOfSavedDevice: String,

    @ColumnInfo (name = "saved_device_image")
    val imageIdOfSavedDevice: Int,

    @ColumnInfo (name = "saved_device_name")
    val nameOfSavedDevice: String

) : Serializable

/**Parcelization is a mechanism that converts complex data objects into a simple format that
 can be easily transfer between activities or fragment.**/