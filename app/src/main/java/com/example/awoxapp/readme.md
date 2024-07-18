

//    private fun confirmPasswordAlertDialog(){
//
//
//        MaterialAlertDialogBuilder(this)
//            .setTitle(R.string.confirm_password_alert_dialog_title)
//            .setIcon(R.drawable.baseline_warning_amber_24)
//            .setMessage(R.string.confirm_password_alert_dialog_message)
//            .setNeutralButton(R.string.confirm_password_alert_dialog_button){_, _ ->}
//            .create()
//            .show()
//
//    }

------------------------------------------------------------------------------


//        val passwordTextWatcher = object : TextWatcher {
//
//            override fun afterTextChanged(s: Editable?) {
//
//
//                mBinding.textViewConfirmPasswordFalse.visibility = View.VISIBLE
//                mBinding.textViewPasswordConditions5.visibility = View.VISIBLE
//                mBinding.textViewPasswordConditions1.visibility = View.VISIBLE
//                mBinding.textViewPasswordConditions2.visibility = View.VISIBLE
//                mBinding.textViewPasswordConditions3.visibility = View.VISIBLE
//                mBinding.textViewPasswordConditions4.visibility = View.VISIBLE
//
//
//                val textColorGreen = ContextCompat.getColor(this@RegisterActivity, R.color.green)
//                val password = editTextUserPassword.text.toString()
//
//                if(TextControl.textControlForUpperCase(password)){
//                    mBinding.textViewPasswordConditions1.setTextColor(textColorGreen)
//
//                }
//                if(TextControl.textControlForLowerCase(password)){
//                    mBinding.textViewPasswordConditions2.setTextColor(textColorGreen)
//
//                }
//                if(TextControl.textControlForSymbols(password)){
//                    mBinding.textViewPasswordConditions3.setTextColor(textColorGreen)
//
//                }
//                if(TextControl.textControlForNumbers(password)){
//                    mBinding.textViewPasswordConditions4.setTextColor(textColorGreen)
//
//                }
//                if(password.length >= 8){
//                    mBinding.textViewPasswordConditions5.setTextColor(textColorGreen)
//
//                }
//
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                //TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                //TODO("Not yet implemented")
//            }
//
//
//        }
//
//        editTextUserPassword.addTextChangedListener(passwordTextWatcher)
//    }







----------------------------------------------------------------------


//        editTextUserFirstName = mBinding.usersInfo!!.userFirstName!!
//        editTextUserMiddleName = mBinding.usersInfo!!.userMiddleName!!
//        editTextUserTelephoneNumber =viewOfPhoneNumber.text.toString()
//        editTextUserLastName = mBinding.usersInfo!!.userLastName!!
//        editTextUserEmail = mBinding.usersInfo!!.userEmail!!
//
//        editTextUserPassword = mBinding.usersInfo!!.userPassword!!
//        editTextUserConfirmPassword = mBinding.usersInfo!!.userConfirmPassword!!

-----------------------
//    private lateinit var editTextUserFirstName : String
//    private lateinit var editTextUserMiddleName : String
//    private lateinit var editTextUserLastName : String
//    private lateinit var editTextUserEmail : String
//    private lateinit var editTextUserTelephoneNumber: String
//    private lateinit var editTextUserPassword: String
//    private lateinit var editTextUserConfirmPassword: String

------------------------------

//        view.doOnTextChanged { str, _, _, _ ->
//
//            emptyTag = (
//                            viewOfFirstName.text.isNotEmpty()&&
//                            viewOfLastName.text.isNotEmpty() &&
//                            viewOfEmail.text.isNotEmpty() &&
//                            viewOfPassword.text.isNotEmpty() &&
//                            viewOfConfirmPassword.text.isNotEmpty() &&
//                            viewOfPhoneNumber.text.toString().length == TELEPHONE_NUMBER_LENGTH &&
//                            confirmPassword(viewOfPassword.text.toString(), viewOfConfirmPassword.text.toString())
//                    )
//
//            Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show()

        //            mBinding.registerActivityRegisterButton.isEnabled = emptyTag

   

