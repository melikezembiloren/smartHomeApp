package com.example.awoxapp.Repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.awoxapp.Repository.entity.Devices

@Dao
interface DevicesDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDevice(device: Devices)

    @Update
    fun updateDevice(device: Devices)

    @Delete
    fun deleteDevice(device: Devices)

    @Query("SELECT EXISTS(SELECT * FROM savedDevices WHERE saved_device_name LIKE :deviceName)")
    fun findByDeviceName(deviceName: String?): Boolean
    @Query("SELECT * FROM savedDevices " )
    fun getAllAddedDevices(): List<Devices>



}