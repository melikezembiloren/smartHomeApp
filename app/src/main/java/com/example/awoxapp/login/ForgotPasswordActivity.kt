package com.example.awoxapp.login

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityForgotPasswordBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
    }

    private fun initData(){
        auth = Firebase.auth
    }

    private fun backButtonClicked(){
        mBinding.backButton.setOnClickListener{finish()}
    }

    private fun resetPassword(){
        val editTextEmail = mBinding.editTextResetPasswordEmail.text.toString()

        if(Patterns.EMAIL_ADDRESS.matcher(editTextEmail).matches()) {

            if (editTextEmail.isNotEmpty()) {
                auth.sendPasswordResetEmail(editTextEmail)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset password successful", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "Reset password failure", Toast.LENGTH_LONG).show()
                        }

                    }
            } else {
                mBinding.editTextResetPasswordEmail.error = getString(R.string.login_empty_email)
            }
        }else{
            mBinding.editTextResetPasswordEmail.error = getString(R.string.login_invalid_email)
        }
    }

    private fun resetPasswordButtonClicked(){

                mBinding.resetPasswordButton.setOnClickListener{resetPassword()}
    }

    private fun initialize(){
        initBinding()
        initData()
        backButtonClicked()
        resetPasswordButtonClicked()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)


        initialize()

    }
}