package com.example.a_level.mypage

import com.google.gson.annotations.SerializedName

data class WrittenPostDTO(
    @SerializedName("numPost")
    val numPost: Int,

    @SerializedName("body")
    val body: String,
)
