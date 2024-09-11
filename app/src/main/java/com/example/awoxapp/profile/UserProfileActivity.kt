package com.example.awoxapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.MainActivity
import com.example.awoxapp.R
import com.example.awoxapp.StoreActivity
import com.example.awoxapp.databinding.ActivityUserProfileBinding
import com.example.awoxapp.login.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class userProfileActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityUserProfileBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mBottomSheetDialogFragment: BottomSheetDialogFragment

    private fun signOutAlertDialog(){



        AlertDialog.Builder(this)
            .setTitle("Çıkış Yap")
            .setMessage("Çıkış yapmak istediğinize emin misiniz?")
            .setNegativeButton(R.string.add_device_alert_dialog_negative_button){ _, _ ->}
            .setPositiveButton("Evet"){ _, _ ->
                mAuth.signOut()
                val intent = Intent(this, LoginActivity::class.java )
                startActivity(intent)
            }.create()
            .show()
    }

    private fun navigationContactButtonClicked(){

        mBottomSheetDialogFragment = FragmentContact()
        mBinding.buttonContact.setOnClickListener { mBottomSheetDialogFragment.show(supportFragmentManager, "Contact") }

    }

    private fun navigationHelpButtonClicked(){

        mBottomSheetDialogFragment = FragmentHelp()
        mBinding.buttonHelp.setOnClickListener { mBottomSheetDialogFragment.show(supportFragmentManager, "Help") }

    }



    private fun navigationBottomHomeClicked(){

        val intent = Intent(this, MainActivity::class.java)
        mBinding.homeActivityButton.setOnClickListener{startActivity(intent)}
    }

    private fun navigationBottomStoreButtonClicked()
    {
        val intent = Intent(this, StoreActivity::class.java)
        mBinding.storeActivityButton.setOnClickListener{startActivity(intent)}
    }

    private fun navigationBottomDevicesButtonClicked()
    {
        val intent = Intent(this, StoreActivity::class.java)
        mBinding.myDevicesActivityButton.setOnClickListener { startActivity(intent) }
    }

    private fun signOutButtonClicked(){


        mBinding.buttonSignOut.setOnClickListener { signOutAlertDialog() }

    }

    private fun settingProfileButtonClicked(){

        mBinding.buttonAccountSettings.setOnClickListener { startActivity(Intent(this, ProfileSettingsActivity::class.java)) }
    }

    private fun initBinding(){

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        mAuth = Firebase.auth
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_profile)

        initBinding()

        if(mAuth.currentUser != null){

            mBinding.name = mAuth.currentUser!!.displayName

        }else
            mBinding.name = "Hoş Geldiniz"


        navigationContactButtonClicked()
        navigationHelpButtonClicked()
        navigationBottomHomeClicked()
        navigationBottomStoreButtonClicked()
        navigationBottomDevicesButtonClicked()
        signOutButtonClicked()
        settingProfileButtonClicked()

        //Toast.makeText(this, mAuth.currentUser!!.email, Toast.LENGTH_LONG).show()

    }
}