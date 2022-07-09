package com.example.a_level.mypage

import com.google.gson.annotations.SerializedName

data class WrittenCommentDTO(
    @SerializedName("numComment")
    val numPost: Int,

    @SerializedName("body")
    val body: String,

)
