package com.example.awoxapp.Repository.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.awoxapp.Repository.database.DevicesDAO
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.Repository.entity.Devices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicesViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData: List<Devices>
    private val repository: DevicesRepository

    init {
        val devicesDAO = DevicesDataBase.getDeviceDataBase(application).devicesDAO()
        repository = DevicesRepository(devicesDAO)
        getAllData = devicesDAO.getAllAddedDevices()
    }

    fun saveDevice(device : Devices) =
        viewModelScope.launch( Dispatchers.IO ){repository.saveDevice(device)}

    fun updateDevice(device : Devices)  =
        viewModelScope.launch( Dispatchers.IO ){repository.updateDevice(device)}

    fun deleteDevice(device : Devices)  =
        viewModelScope.launch( Dispatchers.IO ){repository.deleteDevice(device)}

    fun findByDeviceName(deviceName:String) =
        viewModelScope.launch( Dispatchers.IO ){repository.findByDeviceName(deviceName)}
}