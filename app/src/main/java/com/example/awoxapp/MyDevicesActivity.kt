package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.adapter.RecyclerViewAdapter
import com.example.awoxapp.data.DeviceList
import com.example.awoxapp.databinding.ActivityMyDevicesBinding
import com.example.awoxapp.profile.userProfileActivity
import kotlin.concurrent.thread


class MyDevicesActivity : AppCompatActivity(){

    private lateinit var mBinding: ActivityMyDevicesBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    private lateinit var db : DevicesDataBase


    private fun initDB()
    {
        db = DevicesDataBase.getDeviceDataBase(this)
    }

    private fun initBinding(){

        mBinding = DataBindingUtil.setContentView(this@MyDevicesActivity, R.layout.activity_my_devices)
    }


    private fun removeAt(position: Int){

        val device = DeviceList.deviceObjectList.get(position)
        val devicesDao = db.devicesDAO() // dao nesnesi olusturuldu



        val  thread = thread {
            devicesDao.deleteDevice(device)
            Log.d("main activity", "thread")
            DeviceList.deviceObjectList.removeAt(position)
        }


        thread.join()//threadın bitmesini bekler

    }

    private fun savedDevicesPopUpMenu(position: Int, view: View){
        val popupMenu = android.widget.PopupMenu(this@MyDevicesActivity, view)
        popupMenu.menuInflater.inflate(R.menu.saved_device_popup, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.header1 -> {
                    removeAt(position)
                    rvAdapter.notifyDataSetChanged()
                }
                R.id.header2 -> {
                    navigateSavedDevicesSettingsActivity()
                }
            }
            true
        }

    }


    private fun savedDevicesPopUpMenuClicked(){

        val popUpMenuClickListener = object : RecyclerViewAdapter.OnPopUpMenuClickListener {
            override fun onMenuClicked(position: Int, view: View) {
                savedDevicesPopUpMenu(position,view)

                //  Toast.makeText(this@MainActivity, "ONCLİCK $position", Toast.LENGTH_LONG).show()
            }
        }
        rvAdapter.setOnPopUpMenuClickListener(popUpMenuClickListener)
    }

    /**private fun initSavedDevicesPopUpMenu(){

    val popUpMenuItemClickListener = object : RecyclerViewAdapter.OnPopUpMenuItemClickListener {
    override fun onMenuItemClicked(position: Int) {
    Toast.makeText(this@MainActivity, "Delete clicked for position: $position", Toast.LENGTH_SHORT).show()

    }
    }

    rvAdapter.setOnPopUpMenuItemClickListener(popUpMenuItemClickListener)
    }**/


    private fun navigateSavedDevicesSettingsActivity(){
        val intent = Intent(this, SavedDevicesSettingsActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView(){

        mRecyclerView = mBinding.recyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvAdapter = RecyclerViewAdapter(this, DeviceList.deviceObjectList)
        mRecyclerView.adapter = rvAdapter
        savedDevicesPopUpMenuClicked()

    }


    private fun list() {

        val devicesDao = db.devicesDAO() // dao nesnesi olusturuldu

        DeviceList.deviceObjectList.clear()
        IdGenerator.idMap.clear()
        val t = Thread {
            DeviceList.deviceObjectList.addAll(devicesDao.getAllAddedDevices())



            Log.d("Main Activity", "in thread")

            runOnUiThread {
                Log.d("Main Activity", "outside thread")

                Toast.makeText(
                    this,
                    mRecyclerView.adapter?.itemCount.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                // Toast.makeText(this, "size :${DeviceList.deviceObjectList.size}", Toast.LENGTH_SHORT).show()
                for (device in DeviceList.deviceObjectList) {
                    Log.d("Main activity", "ID generated: ${device.idOfSavedDevice}")

                    var tip = ""
                    when (device.typeOfSavedDevice) {
                        "Kahve Makinesi" -> tip = "cm"

                        "Airfryer" -> tip = "af"

                        "Ocak" -> tip = "ct"

                        "Fırın" -> tip = "ov"

                        "Davlumbaz" -> tip = "ch"

                        "Bulaşık Makinesi" -> tip = "dw"

                    }
                    IdGenerator.generateId(tip)



                }
                rvAdapter.notifyDataSetChanged()
            }
        }

        t.start()

    }

    private fun showAddDevicePopUpMenu() {
        val popup = PopupMenu(this, mBinding.addDeviceButton)

        popup.menuInflater.inflate(R.menu.add_device_menu_button, popup.menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.header1 -> {
                    navigateDeviceCategoriesPage()
                }

                R.id.header2 -> {
                    Toast.makeText(this@MyDevicesActivity, item.title, Toast.LENGTH_SHORT).show()
                }
            }

            true

        }

        popup.show()
    }


    private fun addDeviceButtonClicked() {

        mBinding.addDeviceButton.setOnClickListener { showAddDevicePopUpMenu() }

    }

    private fun navigateDeviceCategoriesPage() {

        val intent = Intent(this@MyDevicesActivity, AddDeviceActivity::class.java)
        startActivity(intent)
    }


    private fun navigationBottomHomeClicked(){

        val intent = Intent(this@MyDevicesActivity, MainActivity::class.java)
        mBinding.homeActivityButton.setOnClickListener{startActivity(intent)}
    }

    private fun navigationBottomStoreButtonClicked()
    {
        val intent = Intent(this@MyDevicesActivity, StoreActivity::class.java)
        mBinding.storeActivityButton.setOnClickListener{startActivity(intent)}
    }

    private fun navigationBottomProfileButtonClicked()
    {
        val intent = Intent(this, userProfileActivity::class.java)
        mBinding.myAccountActivityButton.setOnClickListener { startActivity(intent) }
    }

    private fun initialize() {

        initDB()
        initBinding()
        initRecyclerView()

        addDeviceButtonClicked()

        navigationBottomHomeClicked()
        navigationBottomStoreButtonClicked()
        navigationBottomProfileButtonClicked()
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




