package com.example.a_level.login

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data
)
{
    data class Data(
        @SerializedName("id") val id: Long,
        @SerializedName("token") val token: String,
    )
}

data class LoginRequest(
    val email: String,
    val password: String
)