package com.example.awoxapp.devicesListActivities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityTvBinding
import com.example.awoxapp.listAdapter.ListAdapter

class TelevisionActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityTvBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: DevicesListData

    var dataArrayList = ArrayList<DevicesListData?>()


    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@TelevisionActivity, R.layout.activity_tv)

    }
    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{ finish()}
    }

    private fun list()
    {

        val nameList = arrayOf(
            "Televizyon"
        )
        val imageList = intArrayOf(
            R.drawable.devices_list_television
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