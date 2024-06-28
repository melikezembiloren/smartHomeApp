package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.Repository.repository.DevicesViewModel
import com.example.awoxapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var vm : DevicesViewModel
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    private fun showAddDevicePopUpMenu(){
        val popup = PopupMenu(this, mBinding.addDeviceButton)

        popup.menuInflater.inflate(R.menu.add_device_menu_button, popup.menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->

                when(item!!.itemId) {
                R.id.header1 -> { navigateDeviceCategoriesPage() }

                R.id.header2 -> {
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                }

                }

            true

        }

        popup.show()
    }

    private fun addDeviceButtonClicked(){

        mBinding.addDeviceButton.setOnClickListener{showAddDevicePopUpMenu()}

    }

    private fun navigateDeviceCategoriesPage(){

        val intent = Intent(this@MainActivity, AddDeviceActivity::class.java)
        startActivity(intent)

    }

    private fun initialize()
    {
        initBinding()
        addDeviceButtonClicked()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }


}