package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.awoxapp.Repository.database.DevicesDataBase
import com.example.awoxapp.adapter.RecyclerViewAdapter
import com.example.awoxapp.data.DeviceList
import com.example.awoxapp.databinding.FragmentMyDevicesBinding
import kotlin.concurrent.thread

class MyDevicesFragment : Fragment() {

    private lateinit var mBinding: FragmentMyDevicesBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    private lateinit var db : DevicesDataBase

    private fun initDb(){

        db = DevicesDataBase.getDeviceDataBase(requireContext())
    }

    private fun initRecyclerView(){

        mRecyclerView = mBinding.recyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvAdapter = RecyclerViewAdapter(requireContext(), DeviceList.deviceObjectList)
        mRecyclerView.adapter = rvAdapter
        savedDevicesPopUpMenuClicked()

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

    private fun navigateSavedDevicesSettingsActivity(){
        val intent = Intent(requireContext(), SavedDevicesSettingsActivity::class.java)
        startActivity(intent)
    }


    private fun savedDevicesPopUpMenu(position: Int, view: View){
        val popupMenu = android.widget.PopupMenu(requireContext(), view)
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

    private fun list() {

        val devicesDao = db.devicesDAO() // dao nesnesi olusturuldu

        DeviceList.deviceObjectList.clear()
        IdGenerator.idMap.clear()
        val t = Thread {
            DeviceList.deviceObjectList.addAll(devicesDao.getAllAddedDevices())



            Toast.makeText(
                    requireContext(),
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


        t.start()

    }

    private fun navigateDeviceCategoriesPage() {

        val intent = Intent(requireContext(), AddDeviceActivity::class.java)
        startActivity(intent)
    }


    private fun showAddDevicePopUpMenu() {
        val popup = PopupMenu(requireContext(), mBinding.addDeviceButton)

        popup.menuInflater.inflate(R.menu.add_device_menu_button, popup.menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.header1 -> {
                    navigateDeviceCategoriesPage()
                }

                R.id.header2 -> {
                    Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
                }
            }

            true

        }

        popup.show()
    }

    private fun addDeviceButtonClicked() {

        mBinding.addDeviceButton.setOnClickListener { showAddDevicePopUpMenu() }

    }


    private fun initialize(){
        initDb()
        initRecyclerView()
        list()
        addDeviceButtonClicked()
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_devices, container, false)

        initialize()

        return mBinding.root

    }

}