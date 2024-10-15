package com.example.awoxapp.profile

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.saveable.autoSaver
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityUpdatePasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class UpdatePasswordActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityUpdatePasswordBinding
    private lateinit var mAuth: FirebaseAuth

    private fun initBinding(){

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_password)
        mAuth = Firebase.auth

    }

    private fun backButtonClicked(){

        mBinding.backButton.setOnClickListener{finish()}
    }

    private fun alertDialog(){

        AlertDialog.Builder(this)
            .setMessage("Şifreniz güncellendi")
            .setNeutralButton("Tamam"){_,_ ->}
            .create()
            .show()

    }

    private fun updatePassword() {

        val oldPassword = mBinding.editTextLoginPasswordOld.text.toString()
        val newPassword = mBinding.editTextLoginPasswordNew.text.toString()

        val user = mAuth.currentUser!!

        val credential =
            user.email?.let { EmailAuthProvider.getCredential(it, oldPassword) }

        user.reauthenticate(credential!!).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                user.updatePassword(newPassword)

                alertDialog()


            } else {
                task.exception?.let {
                    val rootView: View = findViewById(android.R.id.content)
                    Snackbar.make(
                        rootView,
                        "Şifre Güncelleme Başarısız: ${it.localizedMessage}",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }


            }

        }
    }

    private fun saveButtonClicked(){

        mBinding.saveButton.setOnClickListener{updatePassword()}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_password)

        initBinding()
        backButtonClicked()
        saveButtonClicked()

    }
}