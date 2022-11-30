package com.example.a_level.keyword

import com.google.gson.annotations.SerializedName

data class PreferenceRequest(
    @SerializedName("user_id") val user_id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("volumne") val volume: String,
    @SerializedName("sugar") val sugar:Int,
    @SerializedName("flavor") val flavor: String,
    @SerializedName("price") val price: String
)

data class PreferenceResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: String
)