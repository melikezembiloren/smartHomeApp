package com.example.awoxapp.devicesListActivities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityCleaningDevicesBinding
import com.example.awoxapp.listAdapter.ListAdapter

class CleaningDevicesActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityCleaningDevicesBinding
    private lateinit var listData: DevicesListData
    private lateinit var listAdapter: ListAdapter

    var dataArrayList = ArrayList<DevicesListData?>()

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@CleaningDevicesActivity, R.layout.activity_cleaning_devices)

    }
    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{ finish()}
    }

    private fun list()
    {

        val nameList = arrayOf(
            "Çamaşır Makinesi",
            "Kurutma Makinesi",
            "Çamaşır & Kurutma Makinesi"
        )
        val imageList = intArrayOf(
            R.drawable.devices_list_washing_machine,
            R.drawable.devices_list_dryer,
            R.drawable.devices_list_dryer_washingmachine,
        )

        for(i in imageList.indices) {
            listData = DevicesListData(nameList[i], imageList[i])
            dataArrayList.add(listData)
        }

        listAdapter = ListAdapter(this, dataArrayList)
        mBinding.listView.adapter = listAdapter
        mBinding.listView.isClickable = true //xml'de cardview clickible false olacak

    }

    private fun addDeviceAlertDialog(){

    }


    private fun initialize()
    {
        initBinding()
        backButtonClicked()
        list()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitchen_devices)

        initialize()

    }
}