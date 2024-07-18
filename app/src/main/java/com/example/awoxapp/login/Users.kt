package com.example.awoxapp.login

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable


@IgnoreExtraProperties
data class Users(
    var userFirstName : String? = "",
    var userMiddleName: String? = "",
    var userLastName: String? = "",
    var userEmail: String? = "",
    var userTelephoneNumber: Number? = 0,
    var userPassword: String? = "",
    var userConfirmPassword: String? = ""


):Serializable