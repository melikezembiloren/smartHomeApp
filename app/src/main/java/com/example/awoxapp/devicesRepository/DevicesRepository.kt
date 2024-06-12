package com.example.awoxapp.devicesRepository

import com.example.awoxapp.database.DevicesDataBase
import com.example.awoxapp.model.Devices

class DevicesRepository(private val db: DevicesDataBase) {

    suspend fun insertDevice( devices: Devices) = db.getDevicesDao().insertDevices(devices)
    suspend fun deleteDevice( devices: Devices) = db.getDevicesDao().deleteDevices(devices)
    suspend fun updateDevice( devices: Devices) = db.getDevicesDao().updateDevices(devices)
    suspend fun getAllDevices() = db.getDevicesDao().getAllAddedDevices()
    suspend fun searchDevices(query:String?) = db.getDevicesDao().searchDevices(query)

}