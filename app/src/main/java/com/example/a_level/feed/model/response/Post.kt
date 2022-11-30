package com.example.a_level.feed.model.response

import com.google.gson.annotations.SerializedName

data class Post(
    val id: Long?,
    @SerializedName("user_id")
    val userId: Long?,
    val title: String?,
    val content: String?,
    val image: String?,
    val hit: Long?,
    val alcoholName: String?,
    val alcoholType: String?,
    val flavor: String?,
    val volume: String?,
    val price: Int?,
    val body: Int?,
    val sugar: Int?,
    @SerializedName("created_date")
    val createdDate: String?,
    @SerializedName("modified_date")
    val modifiedDate: String?,
    val status: Int?,
    val commentCount: Int?,
    val scrapCount: Int?,
    val likeCount: Int?
)
