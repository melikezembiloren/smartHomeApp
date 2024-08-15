package com.example.awoxapp.detailedDevicesActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityControlTvBinding

class controlTvActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityControlTvBinding






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_tv)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_control_tv)

        mBinding.title = intent.getStringExtra("DEVICE_NAME")





    }
}