package com.example.a_level.api

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class LoginResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("id") val id: Int,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("name") val name: String,
        @SerializedName("status") val status: Int,
    )
}