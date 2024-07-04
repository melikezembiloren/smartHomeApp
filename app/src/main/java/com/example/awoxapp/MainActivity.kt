package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.adapter.RecyclerViewAdapter
import com.example.awoxapp.data.DeviceList
import com.example.awoxapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter

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
        rvAdapter = RecyclerViewAdapter(this, DeviceList.deviceObjectList)
        mRecyclerView.adapter = rvAdapter
    }

    private fun list(){
        val devicesDao = db.devicesDAO() // dao nesnesi olusturuldu
        DeviceList.deviceObjectList.clear()
        IdGenerator.idMap.clear()
        val t = Thread { //tum eklenmis cihazlari listelemek icin room islemleri baslatilir
            DeviceList.deviceObjectList.addAll( devicesDao.getAllAddedDevices())



            Log.d("Main Activity", "in thread")

            runOnUiThread{
                Log.d("Main Activity", "outside thread")



                Toast.makeText(this, mRecyclerView.adapter?.itemCount.toString(), Toast.LENGTH_SHORT).show()
               // Toast.makeText(this, "size :${DeviceList.deviceObjectList.size}", Toast.LENGTH_SHORT).show()
                for (device in DeviceList.deviceObjectList){
                    Log.d("Main activity", "ID generated: ${device.idOfSavedDevice}")

                    var tip = ""
                    when(device.typeOfSavedDevice){
                        "Kahve Makinesi" -> tip  = "cm"

                        "Airfryer" -> tip  = "af"

                        "Ocak" -> tip  = "ct"

                        "Fırın" -> tip = "ov"

                        "Davlumbaz" -> tip = "ch"

                        "Bulaşık Makinesi" -> tip  = "dw"

                    }
                    IdGenerator.generateId(tip)
                }
                rvAdapter.notifyDataSetChanged()
            }
        }

        t.start()

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
        list()
        super.onStart()
    }

    }



