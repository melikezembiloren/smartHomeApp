package com.example.awoxapp.Repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.awoxapp.Repository.entity.Devices

@Database(entities = [Devices::class], version = 2, exportSchema = false)

abstract class DevicesDataBase: RoomDatabase() {

    abstract fun devicesDAO() : DevicesDAO

    companion object {


        @Volatile
        private var INSTANCE: DevicesDataBase? = null



        val migrationIntToString: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Geçici bir tablo oluşturun
                db.execSQL("CREATE TABLE savedDevices_temp (" +
                        "idOfSavedDevice TEXT PRIMARY KEY NOT NULL, " +
                        "typeOfSavedDevice TEXT, " +
                        "imageIdOfSavedDevice INTEGER, " +
                        "nameOfSavedDevice TEXT)")

                // Step 2: Copy the data from the old table to the temporary table
                db.execSQL("INSERT INTO savedDevices_temp (idOfSavedDevice, typeOfSavedDevice, imageIdOfSavedDevice, nameOfSavedDevice) " +
                        "SELECT saved_device_ID, saved_device_type, saved_device_image, saved_device_name FROM savedDevices")

                // Step 3: Drop the old table
                db.execSQL("DROP TABLE savedDevices")

                // Step 4: Rename the temporary table to the original table name
                db.execSQL("ALTER TABLE savedDevices_temp RENAME TO savedDevices")
            }
        }


        fun getDeviceDataBase(context: Context): DevicesDataBase {



            return INSTANCE ?: synchronized(this){
                //if the INSTANCE is not null, then return it, if it is, then create database
           // synchronized(DevicesDataBase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DevicesDataBase::class.java,
                    "devices_db"

                )
                    .addMigrations(migrationIntToString)
                    .build()

                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}

