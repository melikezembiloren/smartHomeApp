package com.example.awoxapp.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityUpdateMailBinding
import com.example.awoxapp.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class UpdateMailActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityUpdateMailBinding
    private lateinit var mAuth: FirebaseAuth

    private fun initBinding(){

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_mail)
        mAuth = Firebase.auth


    }

    private fun updateEMail(){

        val oldEmail = mBinding.editTextLoginEmailEx.text.toString()
        val newEmail = mBinding.editTextLoginEmailNext.text.toString()

        if (mAuth.currentUser!!.email == oldEmail) {

            mAuth.currentUser!!.verifyBeforeUpdateEmail(newEmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // E-posta güncelleme başarılı

                        MaterialAlertDialogBuilder(this)
                            .setMessage("Doğrulama linki gönderildi, gelen kutunuzu kontrol edin")
                            .setNeutralButton("Tamam"){_,_ ->
                                startActivity(Intent(this, LoginActivity::class.java))
                            }
                            .create()
                            .show()
                        Toast.makeText(this, "E-posta adresi başarıyla güncellendi.", Toast.LENGTH_SHORT).show()


                    } else {
                        // E-posta güncelleme hatası
                        task.exception?.let {
                            Toast.makeText(this, "E-posta adresi güncellenirken hata oluştu: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }




    }

    private fun backButtonClickListener(){

        mBinding.backButton.setOnClickListener{finish()}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_mail)

        initBinding()
        backButtonClickListener()


        mBinding.saveButton.setOnClickListener { updateEMail() }

    }
}