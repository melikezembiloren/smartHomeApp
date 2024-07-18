package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.awoxapp.databinding.ActivityAddDeviceBinding
import com.example.awoxapp.devicesListActivities.CleaningDevicesActivity
import com.example.awoxapp.devicesListActivities.KitchenDevicesActivity
import com.example.awoxapp.devicesListActivities.OtherDevicesActivity
import com.example.awoxapp.devicesListActivities.TelevisionActivity

class AddDeviceActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAddDeviceBinding




    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@AddDeviceActivity, R.layout.activity_add_device)
    }

    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{ finish()}
    }

    private fun navigateKitchenDevicesMenu(){
        val intentKitchen = Intent(this@AddDeviceActivity, KitchenDevicesActivity::class.java)
        startActivity( intentKitchen)
    }

    private fun navigateCleaningDevicesMenu(){

        val intentCleaning = Intent(this@AddDeviceActivity, CleaningDevicesActivity::class.java)
        startActivity( intentCleaning)
    }

    private fun navigateTelevisionsMenu(){

        val intentTelevision = Intent(this@AddDeviceActivity, TelevisionActivity::class.java)
        startActivity( intentTelevision)
    }

    private fun navigateOtherDevicesMenu(){
        val intentOther = Intent(this@AddDeviceActivity, OtherDevicesActivity::class.java)
        startActivity( intentOther)
    }



    private fun initNavigateDevicesMenu(){

        mBinding.kitchenDevicesCardView.setOnClickListener{ navigateKitchenDevicesMenu() }
        mBinding.televisionsCardView.setOnClickListener{ navigateTelevisionsMenu() }
        mBinding.cleaningDevicesCardView.setOnClickListener{ navigateCleaningDevicesMenu() }
        mBinding.otherDevicesCardView.setOnClickListener{ navigateOtherDevicesMenu() }

    }

    private fun glideOperations(){
        Glide
            .with(this@AddDeviceActivity)
            .load(R.drawable.add_device_kitchen)
            .centerCrop()
            .into(mBinding.imageViewAddDeviceKitchen)

        Glide
            .with(this@AddDeviceActivity)
            .load(R.drawable.add_device_bathroom)
            .centerCrop()
            .into(mBinding.imageViewAddDeviceBathroom)

        Glide
            .with(this@AddDeviceActivity)
            .load(R.drawable.add_device_tv)
            .centerCrop()
            .into(mBinding.imageViewAddDeviceTv)

        Glide
            .with(this@AddDeviceActivity)
            .load(R.drawable.add_device_other)
            .centerCrop()
            .into(mBinding.imageViewAddDeviceHome)
    }


    private fun initialize(){
        initBinding()
        glideOperations()
        backButtonClicked()
        initNavigateDevicesMenu()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)



        initialize()


    }
}