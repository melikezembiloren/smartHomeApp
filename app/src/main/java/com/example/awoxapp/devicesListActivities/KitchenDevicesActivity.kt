package com.example.awoxapp.devicesListActivities

import android.bluetooth.BluetoothClass.Device
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.awoxapp.IdGenerator
import com.example.awoxapp.MainActivity
import com.example.awoxapp.R
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.Repository.repository.DevicesViewModel
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityKitchenDevicesBinding
import com.example.awoxapp.listAdapter.ListAdapter
import com.google.android.material.snackbar.Snackbar

class KitchenDevicesActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityKitchenDevicesBinding
    private lateinit var listData: DevicesListData
    private lateinit var listAdapter: ListAdapter

    private var dataArrayList = ArrayList<DevicesListData?>()


    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_kitchen_devices)

    }

    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{ finish()}
    }

    private fun insertDataToDataBase(newDevice: Devices){

        val db = Room.databaseBuilder(
            this,
            DevicesDataBase::class.java, "devices-database"
        ).build()


        Thread {
            db.devicesDAO().saveDevice(newDevice)
            // Veritabanı işlemini gerçekleştirir ve sonucu iş parçacığına döner
        }.start()

    }


    private fun addDeviceAlertDialog(selectedItemImage: Int, selectedItemType: String){

        val dialogLayout = layoutInflater.inflate(R.layout.layout_add_device_alertdialog,null)


        AlertDialog.Builder(this)
            .setTitle(R.string.add_device_alert_dialog_title)
            .setView(dialogLayout)
            .setNegativeButton(R.string.add_device_alert_dialog_negative_button){ _, _ ->}
            .setPositiveButton(R.string.add_device_alert_dialog_positive_button){ _, _ ->

                val addDeviceEditText = dialogLayout.findViewById<EditText>(R.id.edit_text_add_device_alertDialog)
                val savedDeviceName = addDeviceEditText.text.toString()

                //val idOfDevice = IdGenerator.generateId(selectedItemType)

                val newDevice : Devices = Devices(idOfSavedDevice = 0, selectedItemType, selectedItemImage, savedDeviceName )

                if(savedDeviceName.isNotEmpty()){

                    insertDataToDataBase(newDevice)

                }
                else {
                    val rootView: View = findViewById(android.R.id.content)
                    Snackbar.make(rootView, "Cihaz ismi boş bırakılamaz", Snackbar.LENGTH_LONG)
                        .show()
                }
            }.create()
             .show()
    }


    private fun list()
    {

        val nameList = arrayOf(
            "Kahve Makinesi",
            "Airfryer",
            "Buzdolabı & Dondurucu",
            "Ocak",
            "Fırın",
            "Davlumbaz",
            "Bulaşık Makinesi"
        )
        val imageList = intArrayOf(
            R.drawable.devices_list_coffeemachine,
            R.drawable.devices_list_airfryer,
            R.drawable.devices_list_refrigerator,
            R.drawable.devices_list_hob,
            R.drawable.devices_list_oven,
            R.drawable.devices_list_hood,
            R.drawable.devices_list_dishwasher
        )

        for(i in imageList.indices) {
            listData = DevicesListData(nameList[i], imageList[i])
            dataArrayList.add(listData)
        }

        listAdapter = ListAdapter(this, dataArrayList)
        mBinding.listView.adapter = listAdapter
        mBinding.listView.isClickable = true //xml'de cardview clickible false olacak

    }

    private fun initItemClicked() {
        mBinding.listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItemType = dataArrayList[position]?.name
            val selectedItemImage = dataArrayList[position]?.image
            if (selectedItemType  != "Buzdolabı & Dondurucu"){
                addDeviceAlertDialog(selectedItemImage!!, selectedItemType!!)
            }
        }
    }


    private fun initialize()
    {
        initBinding()
        backButtonClicked()
        list()
        initItemClicked()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitchen_devices)

        initialize()


    }
}