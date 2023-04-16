package com.example.a_level.mypage

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UserInfo
)

data class UserInfo(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
