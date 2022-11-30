package com.example.a_level.common.model.request

import com.google.gson.annotations.SerializedName

data class DetailReview(
    @SerializedName("userid")
    val userId: Long,
    val content: String
)