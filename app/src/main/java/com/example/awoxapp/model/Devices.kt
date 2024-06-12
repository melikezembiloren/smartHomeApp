package com.example.awoxapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "addedDevices")
@Parcelize
data class Devices(
    @PrimaryKey(autoGenerate = true)
    val added_device_ID: Int,//since it is unique it is kept as primary key with @PrimaryKey annotation and it automatically increased  with "autoGenerate = true"
    val added_device_type: String,
    val added_device_image: Int,
    val added_saved_device_name: String
):Parcelable

/**Parcelization şs a mechanism that converts complex data objects into a simple format that
 can be easily transfer between activities or fragment.**/