package com.example.awoxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.awoxapp.model.Devices

@Database(entities = [Devices::class], version = 1)
abstract class DevicesDataBase: RoomDatabase() {
    abstract fun getDevicesDao(): DevicesDAO

    companion object{
    @Volatile
    private var instance: DevicesDataBase? = null
    private val LOCK = Any()

    operator fun invoke(context: Context) = instance ?:
    synchronized(LOCK){
        instance ?:
        createDatabase(context).also{
            instance = it
        }
    }

    private fun createDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            DevicesDataBase::class.java,
            "devices_db"
        ).build()
    }
}