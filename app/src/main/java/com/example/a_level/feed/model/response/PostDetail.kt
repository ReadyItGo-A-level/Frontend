package com.example.a_level.feed.model.response

import com.google.gson.annotations.SerializedName

data class PostDetail(
    val id: Long?,
    @SerializedName("user_id")
    val userName: String?,
    val title: String?,
    val content: String?,
    val image: String?,
    val hit: Long?,
    val alcoholName: String?,
    val flavor: String?,
    val volume: Long?,
    val price: String?,
    val body: Int?,
    val sugar: Int?,
    @SerializedName("modified_date")
    val modifiedDate: String?,
    val commentCount: Int?,
    val scrapCount: Int?,
    val likeCount: Int?,
    val comments: List<Comment>
)
