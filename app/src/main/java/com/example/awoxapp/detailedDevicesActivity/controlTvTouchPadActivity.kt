package com.example.awoxapp.detailedDevicesActivity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityControlTvTouchPadBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class controlTvTouchpadActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityControlTvTouchPadBinding
    private lateinit var mBottomSheetDialogFragment: BottomSheetDialogFragment
    private lateinit var mBottomSheetKeyboardFragment: BottomSheetDialogFragment

    private fun backButtonClicked(){

        mBinding.imageButtonArrowBack.setOnClickListener{finish()}
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
        setContentView(R.layout.activity_control_tv_touch_pad)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_control_tv_touch_pad)

        mBinding.title = intent.getStringExtra("DEVICE_NAME")



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

        backButtonClicked()
        numberKeyboardButtonClicked()
        keyboardClicked()






    }
}