package com.example.a_level.keyword

import com.google.gson.annotations.SerializedName

data class PreferenceRequest(
    @SerializedName("type") val type: String,
    @SerializedName("volume") val volume: String,
    @SerializedName("sugar") val sugar:Int,
    @SerializedName("flavor") val flavor: String,
    @SerializedName("price") val price: String
)

data class PreferenceResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: String
)