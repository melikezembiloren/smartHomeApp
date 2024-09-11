package com.example.awoxapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.databinding.ActivityStoreBinding
import com.example.awoxapp.profile.userProfileActivity
import com.example.awoxapp.storeFragments.FragmentStoreHeaters
import com.example.awoxapp.storeFragments.FragmentStoreSmallAppliances
import com.example.awoxapp.storeFragments.FragmentStoreTV
import com.example.awoxapp.storeFragments.FragmentStoreWhiteGoods
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StoreActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityStoreBinding
    private lateinit var mBottomSheetDialogFragment: BottomSheetDialogFragment

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@StoreActivity, R.layout.activity_store)
    }

    private fun backButtonClicked(){
        mBinding.backButton.setOnClickListener{finish()}
    }


    private fun buyNowClicked(button: Button, bottomSheetDialog: BottomSheetDialogFragment, tag:String){

        mBottomSheetDialogFragment = bottomSheetDialog
        button.setOnClickListener{mBottomSheetDialogFragment.show(supportFragmentManager, tag)}


    }


    private fun navigationBottomHomeClicked(){

        val intent = Intent(this@StoreActivity, MainActivity::class.java)
        mBinding.homeActivityButton.setOnClickListener{startActivity(intent)}
    }

    private fun navigationBottomMyDevicesButtonClicked()
    {
        val intent = Intent(this@StoreActivity, MyDevicesActivity::class.java)
        mBinding.myDevicesActivityButton.setOnClickListener{startActivity(intent)}
    }


    private fun navigateBottomProfileButtonClicked()
    {
        val intent = Intent(this, userProfileActivity::class.java)
        mBinding.myAccountActivityButton.setOnClickListener { startActivity(intent) }
    }

    private fun initialize() {

        initBinding()
        backButtonClicked()

        buyNowClicked(mBinding.buyNowButtonTv, FragmentStoreTV(), "Fragment Store TV")
        buyNowClicked(mBinding.buyNowButtonSmallAppliances, FragmentStoreSmallAppliances(), "Fragment Store Small Appliances")
        buyNowClicked(mBinding.buyNowButtonWhiteGoods, FragmentStoreWhiteGoods(), "Fragment Store White Goods")
        buyNowClicked(mBinding.buyNowButtonHeaters, FragmentStoreHeaters(), "Fragment Store Heaters")

        navigationBottomHomeClicked()
        navigationBottomMyDevicesButtonClicked()
        navigateBottomProfileButtonClicked()



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

    }
}