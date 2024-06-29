package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import android.widget.ListView
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.Repository.repository.DevicesViewModel
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityMainBinding
import com.example.awoxapp.listAdapter.ListAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var listData: DevicesListData
    private lateinit var listAdapter: ListAdapter
    private lateinit var listView: ListView

    var dataArrayList = ArrayList<DevicesListData?>()

    private fun initBinding() {

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        listView = mBinding.listMain

    }

    private fun list() {
        // Veritabanı bağlantısını oluşturun
        var db = Room.databaseBuilder(
            applicationContext,
            DevicesDataBase::class.java, "devices-database"
        ).build()

        // Veritabanı DAO nesnesini oluşturun
        var devicesDao = db.devicesDAO()

        // Tüm eklenmiş cihazları almak için Room işlemlerini başlatın
        Thread {
            val devicesList = devicesDao.getAllAddedDevices()

            // UI işlemlerini UI thread'inde yapmak için runOnUiThread kullanın
            runOnUiThread {

                // devicesList'teki her bir Devices nesnesini DevicesListData'ya dönüştürün ve dataArrayList'e ekleyin
                for (device in devicesList) {
                    val listData = DevicesListData(device.nameOfSavedDevice, device.imageIdOfSavedDevice ) // Resim kaynağına gerçek bir kaynak eklemelisiniz
                    dataArrayList.add(listData)
                }

                // ListAdapter'ı güncelleyerek ListView'e verileri bağlayın
                listAdapter = ListAdapter(this, dataArrayList)
                listView.adapter = listAdapter
            }
        }.start()
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
        list()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }


}