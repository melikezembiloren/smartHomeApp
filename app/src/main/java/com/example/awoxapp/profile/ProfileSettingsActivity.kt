package com.example.awoxapp.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityProfileSettingsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth

class ProfileSettingsActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mBinding : ActivityProfileSettingsBinding


    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{finish()}
    }


    private fun updateDisplayName(displayName: String){




        val profileUpdate = UserProfileChangeRequest.Builder()
            .setDisplayName(displayName)
            .build()

        mAuth.currentUser!!.updateProfile(profileUpdate)

 }

    private fun updateDisplayNameAlertDialog(){

        val dialogLayout = layoutInflater.inflate(R.layout.layout_name_update_alertdialog,null)


        AlertDialog.Builder(this)
            .setTitle("Kullanıcı Adını Değiştir")
            .setView(dialogLayout)
            .setNegativeButton(R.string.add_device_alert_dialog_negative_button){ _, _ ->}
            .setPositiveButton(R.string.add_device_alert_dialog_positive_button){ _, _ ->

                val addDeviceEditText = dialogLayout.findViewById<EditText>(R.id.edit_text_add_device_alertDialog)
                val savedDeviceName = addDeviceEditText.text.toString()


                if(savedDeviceName.isNotEmpty()){

                    updateDisplayName(savedDeviceName)


                }
                else {
                    val rootView: View = findViewById(android.R.id.content)
                    Snackbar.make(rootView, "İsim boş bırakılamaz", Snackbar.LENGTH_LONG)
                        .show()
                }
            }.create()
            .show()

    }

    private fun updatePasswordButtonClickListener(){

        mBinding.buttonAccountSettingsUpdatePassword.setOnClickListener {
            startActivity(Intent(this, UpdatePasswordActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile_settings)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_settings)
        mAuth = Firebase.auth

        mBinding.buttonAccountSettingsUpdateDisplayName.setOnClickListener { updateDisplayNameAlertDialog() }
        mBinding.buttonAccountSettingsUpdateEmail.setOnClickListener { startActivity(Intent(this, UpdateMailActivity::class.java)) }

        Toast.makeText(this, mAuth.currentUser!!.email, Toast.LENGTH_LONG).show()

        updatePasswordButtonClickListener()
        backButtonClicked()


    }
}