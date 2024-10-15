package com.example.awoxapp.detailedDevicesActivity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.awoxapp.R
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.adapter.ListAdapter
import com.example.awoxapp.adapter.ListAdapterRemoteControlTV
import com.example.awoxapp.adapter.RecyclerViewAdapter
import com.example.awoxapp.adapter.RecyclerViewAdapterTVRemote
import com.example.awoxapp.data.DeviceList
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.data.PlatformsData
import com.example.awoxapp.databinding.ActivityControlTvBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class controlTvActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityControlTvBinding

    private lateinit var listData : PlatformsData

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapterTVRemote

    private lateinit var mBottomSheetDialogFragment: BottomSheetDialogFragment
    private lateinit var mBottomSheetKeyboardFragment: BottomSheetDialogFragment

    private val dataItemList = ArrayList<PlatformsData>()//başlangıçta boş bir liste


    private fun initRecyclerView() {

        mRecyclerView = mBinding.listView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAdapter = RecyclerViewAdapterTVRemote(this, dataItemList)


        val nameList = arrayOf(

            "Netflix",
            "Spotify",
            "Youtube",
            "Twitch",
            "Exxen",
            "Disney Plus",
            "Prime Video",
            "Hulu"

        )

        val imageList = intArrayOf(

            R.drawable.logo_netflix,
            R.drawable.logo_spotify,
            R.drawable.logo_youtube,
            R.drawable.logo_twitch,
            R.drawable.logo_exxen,
            R.drawable.logo_disney,
            R.drawable.logo_prime_video,
            R.drawable.logo_hulu

        )


//        val p1 = PlatformsData("Netflix", R.drawable.logo_netflix)
//        val p2 = PlatformsData("Disney", R.drawable.logo_disney)
//
//        dataItemList.add(p1)
//        dataItemList.add(p2)
//        rvAdapter.notifyItemInserted(dataItemList.size - 1)
//
//
//        mRecyclerView.adapter = rvAdapter



        for (i in imageList.indices) {
            listData = PlatformsData(nameList[i], imageList[i])
            dataItemList.add(listData)
            rvAdapter.notifyItemInserted(dataItemList.size - 1)
        }


        mRecyclerView.adapter = rvAdapter
    }

    private fun backButtonClicked(){
        mBinding.imageButtonArrowBack.setOnClickListener { finish() }
    }

    private fun numberKeyboardButtonClicked(){

        mBottomSheetDialogFragment = KeyboardFragmentNumbers()
        mBinding.imageButtonNumberKeyboard.setOnClickListener{mBottomSheetDialogFragment.show(supportFragmentManager, "Fragment Number Keyboard")}
    }

    private fun keyboardClicked(){

        mBottomSheetKeyboardFragment = KeyboardFragment()
        mBinding.imageButtonKeyboard.setOnClickListener{mBottomSheetKeyboardFragment.show(supportFragmentManager, "Fragment Word Keyboard")}


    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_tv)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_control_tv)

        val device = intent.getSerializableExtra("DEVICE") as Devices
        mBinding.title = device.nameOfSavedDevice

        val position = intent.getIntExtra("POSITION", -1)

        initRecyclerView()

        val bottomNavigationView = mBinding.bottomNavigation



        bottomNavigationView.setOnItemSelectedListener {item: MenuItem? ->

            when (item!!.itemId) {
                R.id.bottom1 -> {

                    finish()

                   val intent =  Intent(this, controlTvActivity::class.java).apply {
                       putExtra("DEVICE_NAME", mBinding.title)
                       putExtra("DEVICE", device)
                       putExtra("POSITION", position)
                    }


                    startActivity(intent)




                }

                R.id.bottom2 -> {
                    finish()


                    val intent = Intent(this, controlTvTouchpadActivity::class.java).apply {
                        putExtra("DEVICE_NAME", mBinding.title)
                        putExtra("DEVICE", device)
                        putExtra("POSITION", position)

                    }

                        startActivity(intent)


                }

                R.id.bottom3 -> {

                    finish()

                    val intent = Intent(this, TvSettingsActivity::class.java).apply {
                        putExtra("DEVICE_NAME", mBinding.title)
                        putExtra("DEVICE", device)
                        putExtra("POSITION", position)


                    }

                    startActivity(intent)
                }
            }

            true

        }

        backButtonClicked()

        numberKeyboardButtonClicked()
        keyboardClicked()












    }
}