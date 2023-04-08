package com.example.a_level.login

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class SignupRequest(
        val email: String,
        val password: String,
        val username: String
)

data class SignUpResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val data: Int
)

data class CheckUserResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val data: String
)

data class CheckEmailResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val data: String
)
