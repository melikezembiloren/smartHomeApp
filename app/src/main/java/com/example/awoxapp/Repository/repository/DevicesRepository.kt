package com.example.awoxapp.Repository.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.awoxapp.Repository.database.DevicesDAO
import com.example.awoxapp.Repository.entity.Devices

class DevicesRepository(private val dd : DevicesDAO) {

    val getAllData : List<Devices> = dd.getAllAddedDevices()

    fun saveDevice(device: Devices) = dd.saveDevice(device)

    fun updateDevice(device: Devices) = dd.updateDevice(device)

    fun deleteDevice(device: Devices) = dd.deleteDevice(device)

    fun findByDeviceName(deviceName: String) = dd.findByDeviceName(deviceName)




}