package com.example.awoxapp.detailedDevicesActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.IdGenerator
import com.example.awoxapp.MainActivity
import com.example.awoxapp.R
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.adapter.RecyclerViewAdapter
import com.example.awoxapp.data.DeviceList
import com.example.awoxapp.databinding.ActivityTvSettingsBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class TvSettingsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityTvSettingsBinding
    private lateinit var db : DevicesDataBase
    private lateinit var rvAdapter : RecyclerViewAdapter

    private fun guideButtonClicked(){

        mBinding.buttonUsingGuide.setOnClickListener {
            startActivity(Intent(this, ActivityUsingGuide::class.java))
        }
    }

    private fun changeDeviceNameAlertDialog(){

        val dialogLayout = layoutInflater.inflate(R.layout.layout_add_device_alertdialog,null)
        rvAdapter = RecyclerViewAdapter(this, DeviceList.deviceObjectList)

        AlertDialog.Builder(this)
            .setTitle(R.string.add_device_alert_dialog_title)
            .setView(dialogLayout)
            .setNegativeButton(R.string.add_device_alert_dialog_negative_button){ _, _ ->}
            .setPositiveButton(R.string.add_device_alert_dialog_positive_button){ _, _ ->

                val addDeviceEditText = dialogLayout.findViewById<EditText>(R.id.edit_text_add_device_alertDialog)
                val savedDeviceName = addDeviceEditText.text.toString()

                val device = intent.getSerializableExtra("DEVICE") as Devices


                db = DevicesDataBase.getDeviceDataBase(this)
                val devicesDao = db.devicesDAO()

                val  thread = thread {


                    if(savedDeviceName.isNotEmpty()){

                    devicesDao.updateDevice(Devices(device.idOfSavedDevice, device.typeOfSavedDevice, device.imageIdOfSavedDevice, savedDeviceName) )


                }
                else {
                    val rootView: View = findViewById(android.R.id.content)
                    Snackbar.make(rootView, "Cihaz ismi boş bırakılamaz", Snackbar.LENGTH_LONG)
                        .show()
                }

                }

                thread.join()

                startActivity(Intent(this, MainActivity::class.java))

                rvAdapter.notifyDataSetChanged()

            }.create()
            .show()


    }

    private fun removeDeviceAlertDialog(){

        AlertDialog.Builder(this)

            .setTitle("Uyarı")
            .setMessage("Bu cihazı kaldırmak istediğinize emin misiniz?")
            .setNegativeButton("Hayır"){ _, _ ->}
            .setPositiveButton("Evet"){ _, _ -> removeDevice()}
            .create()
            .show()

    }

    private fun removeDevice(){

        db = DevicesDataBase.getDeviceDataBase(this)
        rvAdapter = RecyclerViewAdapter(this, DeviceList.deviceObjectList)

        val position = intent.getIntExtra("POSITION", -1)

        val device = intent.getSerializableExtra("DEVICE") as Devices
        val devicesDao = db.devicesDAO()

        val  thread = thread {
            devicesDao.deleteDevice(device)
            Log.d("main activity", "thread")
            DeviceList.deviceObjectList.removeAt(position)

        }

        thread.join()


        startActivity(Intent(this, MainActivity::class.java))

        rvAdapter.notifyDataSetChanged()

    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tv_settings)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_settings)

        val device = intent.getSerializableExtra("DEVICE") as Devices

        mBinding.title = device.nameOfSavedDevice

        guideButtonClicked()


        val bottomNavigationView = mBinding.bottomNavigation



        bottomNavigationView.setOnItemSelectedListener {item: MenuItem? ->

            when (item!!.itemId) {
                R.id.bottom1 -> {


                    val intent =  Intent(this, controlTvActivity::class.java).apply {
                        putExtra("DEVICE_NAME", mBinding.title)
                    }


                    startActivity(intent)




                }

                R.id.bottom2 -> {

                    val intent = Intent(this, controlTvTouchpadActivity::class.java).apply {
                        putExtra("DEVICE_NAME", mBinding.title.toString())
                    }

                    startActivity(intent)

                }

                R.id.bottom3 -> {
                    val intent = Intent(this, TvSettingsActivity::class.java).apply {
                        putExtra("DEVICE_NAME", mBinding.title.toString())
                    }

                    startActivity(intent)
                }
            }

            true

        }

        mBinding.buttonDeleteDevice.setOnClickListener { removeDeviceAlertDialog() }
        mBinding.buttonUpdateDeviceName.setOnClickListener {  changeDeviceNameAlertDialog() }

    }


}