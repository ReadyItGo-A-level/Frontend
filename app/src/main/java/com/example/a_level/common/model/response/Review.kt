package com.example.a_level.common.model.response

import com.google.gson.annotations.SerializedName

data class Review(
    val id: Integer,
    val alcoholId: Integer,
    @SerializedName("username")
    val userName: String,
    val content: String,
    val date: String
)