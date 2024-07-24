package com.example.awoxapp.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.MainActivity
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {


    private lateinit var mBinding : ActivityLoginBinding

    private lateinit var auth: FirebaseAuth


    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun initData(){
        auth = Firebase.auth

        mBinding.textViewLoginFailure.isVisible = false


        val textSignup = mBinding.TextViewNavigateRegisterPage.text
        val spannableStringTextSignup : SpannableString = SpannableString(textSignup)
        spannableStringTextSignup.setSpan(UnderlineSpan(), 0, spannableStringTextSignup.length,0)
        mBinding.TextViewNavigateRegisterPage.text = spannableStringTextSignup


        val textForgotPassword = mBinding.textViewForgotPassword.text
        val spannableStringTextForgotPassword : SpannableString = SpannableString(textForgotPassword)
        spannableStringTextForgotPassword.setSpan(UnderlineSpan(), 0, spannableStringTextForgotPassword.length,0)
        mBinding.textViewForgotPassword.text = spannableStringTextForgotPassword
    }



    private fun emailVerificationINotCompletedAlertDialog(){

        val view = layoutInflater.inflate(R.layout.custom_dialog_verification_not_completed, null)

        val alert = MaterialAlertDialogBuilder(this)
            .setBackground(getDrawable(R.drawable.transparent))
            .setView(view)

        val dialog = alert.create()


        val button1 = view.findViewById<Button>(R.id.button1)
        button1.setOnClickListener{dialog.dismiss()}

        val button2 = view.findViewById<Button>(R.id.button2)
        button2.setOnClickListener{auth.currentUser!!.sendEmailVerification().addOnCompleteListener(this){
                task ->
            if(task.isSuccessful){
                AlertDialog.Builder(this)
                    .setMessage("Doğrulama linki gönderildi, gelen kutunuzu kontrol edin")
                    .setNeutralButton("Tamam"){_,_ ->}
                    .create()
                    .show()
            }else{
                Toast.makeText(this, "Email verification can't send" + task.exception!!.message,
                    Toast.LENGTH_LONG).show()
            }
        }
        }

        dialog.show()
    }

    private fun login(){

        val email = mBinding.loginEmail.toString()
        val password = mBinding.loginPass.toString()


        if(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            if (password.isNotEmpty()) {

                if(auth.currentUser!!.isEmailVerified) {

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                                navigateMainActivity()
                            } else {
                                Toast.makeText(this, "Login: Failure", Toast.LENGTH_LONG).show()
                                mBinding.textViewLoginFailure.isVisible = true

                            }


                        }
                }else{
                    emailVerificationINotCompletedAlertDialog()
                }

            }else{
                mBinding.editTextLoginPassword.error = getString(R.string.login_empty_password)
            }

        }else if(email.isEmpty()){
            mBinding.editTextLoginEmail.error = getString(R.string.login_empty_email)
        }
        else{
            mBinding.editTextLoginEmail.error = getString(R.string.login_invalid_email)
        }


    }

    private fun navigateMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)

    }
    private fun loginButtonClickListener(){

        mBinding.materialButton.setOnClickListener{login()}

    }

    private fun registerTextViewClickListener(){

        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)

        mBinding.TextViewNavigateRegisterPage.setOnClickListener{startActivity(intent)}

    }

    private fun forgotPasswordTextViewClickListener(){

        val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
        mBinding.textViewForgotPassword.setOnClickListener{startActivity(intent)}


    }

    private fun initialize(){
        initBinding()
        initData()
        loginButtonClickListener()
        registerTextViewClickListener()
        forgotPasswordTextViewClickListener()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()

    }
}