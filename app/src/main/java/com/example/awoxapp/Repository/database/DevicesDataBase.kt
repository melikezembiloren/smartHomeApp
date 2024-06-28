package com.example.awoxapp.Repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.awoxapp.Repository.entity.Devices

@Database(entities = [Devices::class], version = 1, exportSchema = false)
abstract class DevicesDataBase: RoomDatabase() {

    abstract fun devicesDAO() : DevicesDAO

    companion object {
        @Volatile
        private var INSTANCE: DevicesDataBase? = null
        fun getDeviceDataBase(context: Context): DevicesDataBase {

            //if (INSTANCE == null)
            return INSTANCE ?: synchronized(this){
                //if the INSTANCE is not null, then return it, if it is, then create database
           // synchronized(DevicesDataBase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DevicesDataBase::class.java,
                    "devices_db"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}

