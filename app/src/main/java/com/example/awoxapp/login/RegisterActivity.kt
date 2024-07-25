package com.example.awoxapp.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.AddDeviceActivity
import com.example.awoxapp.MainActivity
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import org.intellij.lang.annotations.Pattern
import kotlin.concurrent.thread
import kotlin.properties.Delegates


const val MIN_PASSWORD_LENGTH = 8
const val TELEPHONE_NUMBER_LENGTH = 10

const val PROTECTION_PERSONAL_DATA_URL = "https://www.awox.com.tr/UyelikSozlesme.aspx?sozlemeTipi=5"
const val MEMBERSHIP_AGREEMENT = "https://www.awox.com.tr/UyelikSozlesme.aspx"

val COLOR_ID_FOR_VALID  = R.color.green
val COLOR_ID_FOR_INVALID = R.color.md_theme_error



class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding

    private lateinit var viewOfFirstName : EditText
    private lateinit var viewOfMiddleName : EditText
    private lateinit var viewOfLastName : EditText
    private lateinit var viewOfEmail : EditText
    private lateinit var viewOfPhoneNumber : EditText
    private lateinit var viewOfPassword : EditText
    private lateinit var viewOfConfirmPassword : EditText

    private var emptyTag by Delegates.notNull<Boolean>()
    private var passwordTag by Delegates.notNull<Boolean>()

    private lateinit var auth: FirebaseAuth



    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)

    }


    private fun initData(){

        emptyTag = false
        passwordTag = false


        auth = Firebase.auth

        mBinding.registerActivityRegisterButton.isEnabled = false

        viewOfFirstName = mBinding.registerActivityEditTextUserFirstName
        viewOfMiddleName = mBinding.registerActivityEditTextUserMiddleName
        viewOfLastName = mBinding.registerActivityEditTextUserLastName
        viewOfEmail = mBinding.registerActivityEditTextUserEmail
        viewOfPhoneNumber = mBinding.registerActivityEditTextUserTelephoneNumber
        viewOfPassword = mBinding.registerActivityEditTextPassword
        viewOfConfirmPassword = mBinding.registerActivityEditTextUserConfirmPassword

        val text = mBinding.textViewNavigateLoginPage.text
        val spannableString : SpannableString = SpannableString(text)
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length,0)
        mBinding.textViewNavigateLoginPage.text = spannableString

 }


    private fun initVisibilities(){


        mBinding.textViewConfirmPasswordFalse.visibility = View.GONE
        mBinding.textViewPasswordConditions5.visibility = View.GONE
        mBinding.textViewPasswordConditions1.visibility = View.GONE
        mBinding.textViewPasswordConditions2.visibility = View.GONE
        mBinding.textViewPasswordConditions3.visibility = View.GONE
        mBinding.textViewPasswordConditions4.visibility = View.GONE

    }


    private fun setColor(textview: TextView, colorId: Int){

        val colorValue = ContextCompat.getColor(this@RegisterActivity, colorId)
        textview.setTextColor(colorValue)
    }


    private fun confirmPassword(password: String, confirmPassword:String): Boolean{

        var confirm =  true
        mBinding.textViewConfirmPasswordFalse.visibility = View.GONE

        if (password != confirmPassword){

            confirm = false
            mBinding.textViewConfirmPasswordFalse.visibility = View.VISIBLE

        }


        return confirm
    }


    private fun confirmPasswordTextChangeListener(){

        val passwordTextWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val password = viewOfPassword.text.toString()
                val confirmPassword= viewOfConfirmPassword.text.toString()

                confirmPassword(password, confirmPassword)


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


        }

        viewOfPassword.addTextChangedListener(passwordTextWatcher)
        viewOfConfirmPassword.addTextChangedListener(passwordTextWatcher)

    }



    private fun passwordTextChangeListener(){

        viewOfPassword.doOnTextChanged { _, _, _, _ ->


            mBinding.textViewConfirmPasswordFalse.visibility = View.VISIBLE
            mBinding.textViewPasswordConditions5.visibility = View.VISIBLE
            mBinding.textViewPasswordConditions1.visibility = View.VISIBLE
            mBinding.textViewPasswordConditions2.visibility = View.VISIBLE
            mBinding.textViewPasswordConditions3.visibility = View.VISIBLE
            mBinding.textViewPasswordConditions4.visibility = View.VISIBLE


            val dataUsersPassword = viewOfPassword.text.toString()

            fun isThereUpperCase(): Boolean {

                var result = false

                if (TextControl.textControlForUpperCase(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions1, COLOR_ID_FOR_VALID)

                    result = true

                } else {
                    setColor(mBinding.textViewPasswordConditions1, COLOR_ID_FOR_INVALID)

                    result = false

                }

                return result
            }

            val upCase = isThereUpperCase()

            fun isThereLowerCase(): Boolean {

                var result = false
                if (TextControl.textControlForLowerCase(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions2, COLOR_ID_FOR_VALID)

                    result = true


                } else {
                    setColor(mBinding.textViewPasswordConditions2, COLOR_ID_FOR_INVALID)
                    result = false

                }

                return result
            }

            val lowCase = isThereLowerCase()


            fun isThereSymbols(): Boolean {
                var result = false
                if (TextControl.textControlForSymbols(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions3, COLOR_ID_FOR_VALID)

                    result = true

                } else {
                    setColor(mBinding.textViewPasswordConditions3, COLOR_ID_FOR_INVALID)
                    result = false

                }

                return result
            }

            val symbol = isThereSymbols()


            fun isThereNumber(): Boolean {

                var result = false
                if (TextControl.textControlForNumbers(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions4, COLOR_ID_FOR_VALID)

                    result = true


                } else {
                    setColor(mBinding.textViewPasswordConditions4, COLOR_ID_FOR_INVALID)

                    result = false


                }
                return result
            }

            val number = isThereNumber()


            fun isLengthEnough(): Boolean {

                var result = false

                if (dataUsersPassword.length >= MIN_PASSWORD_LENGTH) {

                    setColor(mBinding.textViewPasswordConditions5, COLOR_ID_FOR_VALID)

                    result = true
                } else {
                    setColor(mBinding.textViewPasswordConditions5, COLOR_ID_FOR_INVALID)

                    result = false

                }


                return result
            }

            val length = isLengthEnough()

            passwordTag = if(upCase && lowCase && symbol && number && length) {
                true
            }else{
                false
            }
        }
    }


    private fun fieldsTextWatcher() {

        val textWatcher = object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {
                    val firstName = viewOfFirstName.text.toString()
                    val lastName = viewOfLastName.text.toString()
                    val email = viewOfEmail.text.toString()
                    val phoneNumber = viewOfPhoneNumber.text.toString()
                    val confirmPassword = viewOfConfirmPassword.text.toString()
                    val password = viewOfPassword.text.toString()



                    fun isFieldEmpty(text: String, view: EditText) : Boolean {

                        if(text.length < 3 ){
                            view.error = getString(R.string.message_empty_text)
                            emptyTag = false

                        }else{
                            view.error = null
                            emptyTag = true
                        }
                        return emptyTag


                    }

                    fun isPhoneNumberFilledValid():Boolean {

                        var result = false

                        if (Patterns.PHONE.matcher(phoneNumber).matches()){
                            result = true
                        }else{
                            mBinding.registerActivityEditTextUserTelephoneNumber.error = getString(R.string.login_invalid_phone_number)
                        }

                        return result
                    }

                    fun isPasswordFieldsIsFilledValid():Boolean {

                        return password.isNotEmpty() && confirmPassword.isNotEmpty()
                    }

                    fun isEmailFormatValid():Boolean {

                        var result = false

                        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                            result = true

                        }else{
                            mBinding.registerActivityEditTextUserEmail.error = getString(R.string.login_invalid_email)
                            result = false

                        }

                        return result
                    }


                    val passwordMatch = confirmPassword(viewOfPassword.text.toString(),
                        viewOfConfirmPassword.text.toString())

                    val allFieldIsFilled = isFieldEmpty(firstName, viewOfFirstName) &&
                        isFieldEmpty(lastName, viewOfLastName) &&
                        isFieldEmpty(email, viewOfEmail) &&
                        isFieldEmpty(phoneNumber, viewOfPhoneNumber)&&
                        isPhoneNumberFilledValid()


                    val phoneNumberIsFilledValid = isPhoneNumberFilledValid()

                    val passwordFieldsIsFilledValid = isPasswordFieldsIsFilledValid()

                    val passwordConditions = passwordTag

                    val emailFormat = isEmailFormatValid()

                   //val checkBoxCondition = mBinding.checkBox.isChecked



                    emptyTag = if(
                        passwordMatch &&
                        passwordConditions &&
                        allFieldIsFilled &&
                        phoneNumberIsFilledValid &&
                        passwordFieldsIsFilledValid &&
                        emailFormat){
                        true

                    }else{
                        false
                    }


                    mBinding.registerActivityRegisterButton.isEnabled = emptyTag


                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }


            }

            viewOfFirstName.addTextChangedListener(textWatcher)
            viewOfLastName.addTextChangedListener(textWatcher)
            viewOfPhoneNumber.addTextChangedListener(textWatcher)
            viewOfEmail.addTextChangedListener(textWatcher)
            viewOfConfirmPassword.addTextChangedListener(textWatcher)
            viewOfPassword.addTextChangedListener(textWatcher)


    }

    private fun alertDialogSignUpSuccessful(){

        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)

        val view = layoutInflater.inflate(R.layout.custom_dialog_signup_successful, null)

        val alert = MaterialAlertDialogBuilder(this)
            .setBackground(getDrawable(R.drawable.transparent))
            .setView(view)

        val dialog = alert.create()

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener{ auth.signOut(); startActivity(intent)}

        dialog.show()




    }

    private fun signUp(){
        val email = viewOfEmail.text.toString()
        val password = viewOfPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this@RegisterActivity){
            task ->
            if (task.isSuccessful){
                auth.currentUser!!.sendEmailVerification().addOnCompleteListener(this){taskVerification ->
                    if(taskVerification.isSuccessful){
                        insertUserInfoFireBaseDatabase()
                        alertDialogSignUpSuccessful()


                    }else{

                        Toast.makeText(this, "SignUp failed" + task.exception!!.message, Toast.LENGTH_LONG).show()

                    }

                }
            }else{
                Toast.makeText(this, "SignUp failed" + task.exception!!.message, Toast.LENGTH_LONG).show()


            }
        }

//        thread{
//            Log.d("THREAD","thread")
//            while (true){
//                if (auth.currentUser!!.isEmailVerified){
//                    runOnUiThread {
//                        Log.d("THREAD","verified")
//                        startActivity(Intent(this@RegisterActivity, AddDeviceActivity::class.java))
//                    }
//
//                    Thread.sleep(500)
//
//                    break;
//                }
//            }
//        }

    }



    private fun insertUserInfoFireBaseDatabase(){

        val dataUsersFirstName = viewOfFirstName.text.toString()
        val dataUsersMiddleName = viewOfMiddleName.text.toString()
        val dataUserLastName = viewOfLastName.text.toString()
        val dataUserEmail = viewOfEmail.text.toString()
        val dataUserTelephoneNumber = viewOfPhoneNumber.text.toString().toDouble()
        val dataUserPassword = viewOfPassword.text.toString()
        val dataUserConfirmPassword = viewOfConfirmPassword.text.toString()


        if(confirmPassword(dataUserPassword, dataUserConfirmPassword)){

            val database = FirebaseDatabase.getInstance()

            val refUsers = database.getReference("Users")//kayıtlı kisiler bu path altına eklenir

            val user = Users(dataUsersFirstName, dataUsersMiddleName, dataUserLastName,
                dataUserEmail, dataUserTelephoneNumber, dataUserPassword,
                dataUserConfirmPassword)

            refUsers.push().setValue(user)

            Toast.makeText(this, "data is  pushed", Toast.LENGTH_LONG).show()

        }



    }



    private fun alertDialogMemberShipAgreement() {

        val maDialogView = layoutInflater.inflate(R.layout.dialog_member_ship_agreement, null)

        val webViewMemberShipAgreement: WebView = maDialogView.findViewById(R.id.webViewMemberShip)

        val alert = MaterialAlertDialogBuilder(this)
            .setView(maDialogView)
        val dialog = alert.create()
//            dialog.setCancelable(false)



        val positiveButton = maDialogView.findViewById<Button>(R.id.accept_button)
        positiveButton.setOnClickListener{alertDialogProtectionOfPersonalData()}

        val negativeButton = maDialogView.findViewById<Button>(R.id.cancel_button)
        negativeButton.setOnClickListener{dialog.dismiss()}


        dialog.show()


        webViewMemberShipAgreement.settings.javaScriptEnabled = true



            positiveButton.isEnabled = false
            webViewMemberShipAgreement.addJavascriptInterface( WebAppInterface(this, positiveButton ), "Android");


            webViewMemberShipAgreement.setWebViewClient(object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    webViewMemberShipAgreement.loadUrl(
                        "javascript:(function() { " +
                                "window.onscroll = function() { " +
                                "    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) { " +
                                "        Android.onScrollToBottom(); " +
                                "    } " +
                                "}; " +
                                "})()"
                    )
                }
            })

            webViewMemberShipAgreement.loadUrl(MEMBERSHIP_AGREEMENT)


    }



    private fun alertDialogProtectionOfPersonalData(){


        val popdDialogView = layoutInflater.inflate(R.layout.dialog_protection_of_personal_data, null)

        val webViewProtectionPersonalData: WebView = popdDialogView.findViewById(R.id.webViewProtectionOfPersonalData)

        val alert = MaterialAlertDialogBuilder(this)
            .setView(popdDialogView)
        val dialog = alert.create()

        val negativeButton = popdDialogView.findViewById<Button>(R.id.cancel_button)
        negativeButton.setOnClickListener{dialog.dismiss()}

        val positiveButton = popdDialogView.findViewById<Button>(R.id.accept_button)
        positiveButton.setOnClickListener{signUp()}

        dialog.show()


        webViewProtectionPersonalData.settings.javaScriptEnabled = true



        positiveButton.isEnabled = false
        webViewProtectionPersonalData.addJavascriptInterface( WebAppInterface(this, positiveButton ), "Android");


        webViewProtectionPersonalData.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                webViewProtectionPersonalData.loadUrl(
                    "javascript:(function() { " +
                            "window.onscroll = function() { " +
                            "    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) { " +
                            "        Android.onScrollToBottom(); " +
                            "    } " +
                            "}; " +
                            "})()"
                )
            }
        })

        webViewProtectionPersonalData.loadUrl(PROTECTION_PERSONAL_DATA_URL)


    }


    private fun alreadyHaveAnAccountTextClicked(){

        val intent = Intent(this, LoginActivity::class.java)
        mBinding.textViewNavigateLoginPage.setOnClickListener{startActivity(intent)}
    }

    private fun registerButtonClicked() {


        mBinding.registerActivityRegisterButton.apply {
            setOnClickListener {alertDialogMemberShipAgreement()}
        }
    }

    private fun initListeners(){
        fieldsTextWatcher()
        confirmPasswordTextChangeListener()
        passwordTextChangeListener()

    }

    private fun backButtonClicked(){

        val intent = Intent(this, LoginActivity::class.java)

        mBinding.backButton.setOnClickListener{startActivity(intent)}

    }


    private fun initialize(){
        initBinding()
        initData()
        initVisibilities()
        initListeners()
        registerButtonClicked()
        alreadyHaveAnAccountTextClicked()
        backButtonClicked()

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initialize()


    }


//    fun allowUsingConditionsCheckBox(checked: Boolean){
//
//        if (checked){
//            emptyTag = true
//            mBinding.registerActivityRegisterButton.isEnabled = true
//        }else{
//
//            mBinding.registerActivityRegisterButton.isEnabled = false
//        }
//    }
}