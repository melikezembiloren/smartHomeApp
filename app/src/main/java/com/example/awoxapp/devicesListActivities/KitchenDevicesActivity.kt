package com.example.awoxapp.devicesListActivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityKitchenDevicesBinding
import com.example.awoxapp.listAdapter.ListAdapter

class KitchenDevicesActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityKitchenDevicesBinding
    private lateinit var listData: DevicesListData
    private lateinit var listAdapter: ListAdapter

    var dataArrayList = ArrayList<DevicesListData?>()

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_kitchen_devices)
    }

    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{ finish()}
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