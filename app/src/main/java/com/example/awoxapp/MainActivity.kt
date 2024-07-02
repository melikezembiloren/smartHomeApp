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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.databinding.ActivityMainBinding
import com.example.awoxapp.adapter.ListAdapter
import com.example.awoxapp.adapter.RecyclerViewAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    private var dataArrayList = ArrayList<DevicesListData?>()
    private lateinit var db : DevicesDataBase

    private fun initDB()
    {
        db = DevicesDataBase.getDeviceDataBase(this)
    }

    private fun initBinding(){

        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
    }


    private fun initRecyclerView(){

        mRecyclerView = mBinding.recyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvAdapter = RecyclerViewAdapter(this, dataArrayList)
        mRecyclerView.adapter = rvAdapter
    }

    private fun list(){

        dataArrayList.clear()

        val devicesDao = db.devicesDAO() // dao nesnesi olusturuldu

        Thread { //tum eklenmis cihazlari listelemek icin room islemleri baslatilir
            val devicesList = devicesDao.getAllAddedDevices()


            runOnUiThread {//ui islemleri

                for (device in devicesList) {// devicesList'teki her bir Devices nesnesini DevicesListData'ya dönüştürün ve dataArrayList'e ekleyin
                    val listData = DevicesListData(device.nameOfSavedDevice, device.imageIdOfSavedDevice )
                    dataArrayList.add(listData)
                }



                Toast.makeText(this, mRecyclerView.adapter?.itemCount.toString(), Toast.LENGTH_LONG).show()
                rvAdapter.notifyDataSetChanged()//data setteki degisikligi recyclerviewa bildirmek icin
                
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
        initDB()
        initRecyclerView()
        addDeviceButtonClicked()


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)


        initialize()
    }

    override fun onStart() {
        super.onStart()

        list()

    }

    }



