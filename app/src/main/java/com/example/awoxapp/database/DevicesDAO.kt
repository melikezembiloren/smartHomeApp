package com.example.awoxapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.awoxapp.model.Devices

@Dao
interface DevicesDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: Devices)

    @Update
    suspend fun updateDevices(devices: Devices)

    @Delete
    suspend fun deleteDevices(devices: Devices)

    @Query("SELECT * FROM ADDEDDEVICES ORDER BY added_device_ID DESC" )
    suspend fun getAllAddedDevices(): LiveData<List<Devices>>

    @Query("SELECT * FROM ADDEDDEVICES WHERE added_device_type LIKE:query" )
    suspend fun searchDevices(query: String?): LiveData<List<Devices>>


}