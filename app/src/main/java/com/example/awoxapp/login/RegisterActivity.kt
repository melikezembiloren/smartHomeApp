package com.example.awoxapp.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.awoxapp.R
import com.example.awoxapp.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import kotlin.properties.Delegates


const val MIN_PASSWORD_LENGTH = 8
const val TELEPHONE_NUMBER_LENGTH = 10

const val PROTECTION_PERSONAL_DATA_URL = "https://www.awox.com.tr/UyelikSozlesme.aspx?sozlemeTipi=5"
const val MEMBERSHIP_AGREEMENT = "https://www.awox.com.tr/UyelikSozlesme.aspx"


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

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this@RegisterActivity, R.layout.activity_register)

    }


    private fun initData(){

        emptyTag = false
        passwordTag = false



        mBinding.registerActivityRegisterButton.isEnabled = false

        viewOfFirstName = mBinding.registerActivityEditTextUserFirstName
        viewOfMiddleName = mBinding.registerActivityEditTextUserMiddleName
        viewOfLastName = mBinding.registerActivityEditTextUserLastName
        viewOfEmail = mBinding.registerActivityEditTextUserEmail
        viewOfPhoneNumber = mBinding.registerActivityEditTextUserTelephoneNumber
        viewOfPassword = mBinding.registerActivityEditTextPassword
        viewOfConfirmPassword = mBinding.registerActivityEditTextUserConfirmPassword

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


    private fun initListeners(){
        fieldsTextWatcher()
        confirmPasswordTextChangeListener()
        passwordTextChangeListener()

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


            val colorIdForValid = R.color.green
            val colorIdForNotValid = R.color.red

            val dataUsersPassword = viewOfPassword.text.toString()

            fun isThereUpperCase(): Boolean {

                var result = false

                if (TextControl.textControlForUpperCase(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions1, colorIdForValid)

                    result = true

                } else {
                    setColor(mBinding.textViewPasswordConditions1, colorIdForNotValid)

                    result = false

                }

                return result
            }

            val upCase = isThereUpperCase()

            fun isThereLowerCase(): Boolean {

                var result = false
                if (TextControl.textControlForLowerCase(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions2, colorIdForValid)

                    result = true


                } else {
                    setColor(mBinding.textViewPasswordConditions2, colorIdForNotValid)
                    result = false

                }

                return result
            }

            val lowCase = isThereLowerCase()


            fun isThereSymbols(): Boolean {
                var result = false
                if (TextControl.textControlForSymbols(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions3, colorIdForValid)

                    result = true

                } else {
                    setColor(mBinding.textViewPasswordConditions3, colorIdForNotValid)
                    result = false

                }

                return result
            }

            val symbol = isThereSymbols()


            fun isThereNumber(): Boolean {

                var result = false
                if (TextControl.textControlForNumbers(dataUsersPassword)) {
                    setColor(mBinding.textViewPasswordConditions4, colorIdForValid)

                    result = true


                } else {
                    setColor(mBinding.textViewPasswordConditions4, colorIdForNotValid)

                    result = false


                }
                return result
            }

            val number = isThereNumber()


            fun isLengthEnough(): Boolean {

                var result = false

                if (dataUsersPassword.length >= MIN_PASSWORD_LENGTH) {

                    setColor(mBinding.textViewPasswordConditions5, colorIdForValid)

                    result = true
                } else {
                    setColor(mBinding.textViewPasswordConditions5, colorIdForNotValid)

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
                        //Toast.makeText(this@RegisterActivity, text, Toast.LENGTH_SHORT).show()

                        return emptyTag


                    }

                    fun isPhoneNumberFilled():Boolean {
                        return phoneNumber.length == TELEPHONE_NUMBER_LENGTH && phoneNumber.startsWith('5')
                    }

                    fun isPasswordFieldsIsFulled():Boolean {

                        return password.isNotEmpty() && confirmPassword.isNotEmpty()
                    }


                    val passwordMatch = confirmPassword(viewOfPassword.text.toString(),
                        viewOfConfirmPassword.text.toString())

                    val allFieldIsFilled = isFieldEmpty(firstName, viewOfFirstName) &&
                        isFieldEmpty(lastName, viewOfLastName) &&
                        isFieldEmpty(email, viewOfEmail) &&
                        isFieldEmpty(phoneNumber, viewOfPhoneNumber)&&
                        isPhoneNumberFilled()

                    val phoneNumberIsFilled = isPhoneNumberFilled()

                    val passwordFieldsIsFilled = isPasswordFieldsIsFulled()

                    val passwordConditions = passwordTag

                   //val checkBoxCondition = mBinding.checkBox.isChecked



                    emptyTag = if(
                        passwordMatch &&
                        passwordConditions &&
                        allFieldIsFilled &&
                        phoneNumberIsFilled &&
                        passwordFieldsIsFilled ){
                        true

                    }else{
                        false
                    }


                    mBinding.registerActivityRegisterButton.isEnabled = emptyTag



//                    if(emptyTag && checkBoxCondition){
//                        mBinding.registerActivityRegisterButton.isEnabled = true
//
//                    }else{
//                        mBinding.registerActivityRegisterButton.isEnabled = false
//                    }



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



    private fun insertFireBaseDatabase(){

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

//    private fun scrollChangeListener(){
//
//        val maDialogView = layoutInflater.inflate(R.layout.dialog_member_ship_agreement, null)
//
//        val scrollView : ScrollView = maDialogView.findViewById<ScrollView?>(R.id.scrollViewMembershipAgreement)
//        val view = scrollView.getChildAt(scrollView.childCount - 1)
//
//        val bottomDetector = view.bottom - (scrollView.height + scrollView.scrollY)
//
//        if(bottomDetector == 0){
//            Toast.makeText(this, "Scroll View bottom reached",Toast.LENGTH_SHORT).show()
//        }
//
//    }







    private fun alertDialogMemberShipAgreement() {

        val maDialogView = layoutInflater.inflate(R.layout.dialog_member_ship_agreement, null)


        val webViewMemberShipAgreement: WebView = maDialogView.findViewById(R.id.webViewMemberShip)

           MaterialAlertDialogBuilder(this, R.style.Dialog)
            .setTitle(R.string.accept_membership_agreement_title)
            .setView(maDialogView)
            .setNegativeButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.accept_button) { _, _ ->
                alertDialogProtectionOfPersonalData()
            }.create()
            .show()


        webViewMemberShipAgreement.settings.javaScriptEnabled = true
        val url = MEMBERSHIP_AGREEMENT


            webViewMemberShipAgreement.addJavascriptInterface( WebAppInterface(this), "Android");

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

            webViewMemberShipAgreement.loadUrl(url)


    }



    private fun alertDialogProtectionOfPersonalData(){


        val popdDialogView = layoutInflater.inflate(R.layout.dialog_protection_of_personal_data, null)

        val webViewProtectionPersonalDAta: WebView = popdDialogView.findViewById(R.id.webViewProtectionOfPersonalData)
        webViewProtectionPersonalDAta.webViewClient = WebViewClient()

        MaterialAlertDialogBuilder(this, R.style.Dialog)
            .setTitle(R.string.accept_protection_of_personal_data_title)
            .setView(popdDialogView)
            .setNegativeButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.accept_button) {_,_ ->
                insertFireBaseDatabase()
            }.create()
            .show()


        val url = PROTECTION_PERSONAL_DATA_URL
        webViewProtectionPersonalDAta.loadUrl(url)
    }




    private fun registerButtonClicked() {

        Toast.makeText(this,"button clicked", Toast.LENGTH_LONG).show()

        mBinding.registerActivityRegisterButton.apply {
            setOnClickListener { alertDialogMemberShipAgreement()}
        }
    }

    private fun initialize(){
        initBinding()
        initData()
        initVisibilities()
        initListeners()
        registerButtonClicked()

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