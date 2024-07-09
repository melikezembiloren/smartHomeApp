package com.example.awoxapp.devicesListActivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityOtherDevicesBinding
import com.example.awoxapp.adapter.ListAdapter
import com.example.awoxapp.login.LoginActivity
import com.example.awoxapp.login.RegisterActivity

class OtherDevicesActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityOtherDevicesBinding
    private lateinit var listData: DevicesListData
    private lateinit var listAdapter: ListAdapter

    var dataArrayList = ArrayList<DevicesListData?>()


    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@OtherDevicesActivity, R.layout.activity_other_devices)
    }
    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{ finish()}
    }

    private fun list()
    {

        val nameList = arrayOf(
            ""
        )
        val imageList = intArrayOf(

        )

        for(i in imageList.indices) {
            listData = DevicesListData(nameList[i], imageList[i])
            dataArrayList.add(listData)
        }

        listAdapter = ListAdapter(this, dataArrayList)
        mBinding.listView.adapter = listAdapter
        mBinding.listView.isClickable = true //xml'de cardview clickible false olacak

    }

    private fun loginButton(){
        mBinding.loginpageButton.setOnClickListener{ startActivity(Intent(this, LoginActivity::class.java))}
    }

    private fun registerButton(){
        mBinding.registerPageButton.setOnClickListener{ startActivity(Intent(this, RegisterActivity::class.java))}
    }



    private fun initialize()
    {
        initBinding()
        backButtonClicked()
        list()
        loginButton()
        registerButton()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

    }
}