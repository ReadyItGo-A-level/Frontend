package com.example.a_level.feed.model.response

import com.example.a_level.login.LoginResponse
import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Long
)

data class FeedListResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<FeedList>
)

data class FeedList(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image: String,
    @SerializedName("hit") val hit: Long,
    @SerializedName("commentCount") val commentCount: Long,
    @SerializedName("scrapCount") val scrapCount: Long,
    @SerializedName("likeCount") val likeCount: Long,
    @SerializedName("alcoholName") val alcoholName: String,
    @SerializedName("alcoholType") val alcoholType: String,
    @SerializedName("flavor") val flavor: String,
    @SerializedName("volume") val volume: Long,
    @SerializedName("price") val price: String,
    @SerializedName("body") val body: Int,
    @SerializedName("sugar") val sugar: Long,
    @SerializedName("modifiedDate") val modifiedDate: String
)

data class FeedDetailResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: FeedDetail
)

data class FeedDetail(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image: String,
    @SerializedName("hit") val hit: Long,
    @SerializedName("commentCount") val commentCount: Long,
    @SerializedName("scrapCount") val scrapCount: Long,
    @SerializedName("likeCount") val likeCount: Long,
    @SerializedName("alcoholName") val alcoholName: String,
    @SerializedName("alcoholType") val alcoholType: String,
    @SerializedName("flavor") val flavor: String,
    @SerializedName("volume") val volume: String,
    @SerializedName("price") val price: String,
    @SerializedName("body") val body: Int,
    @SerializedName("sugar") val sugar: Int,
    @SerializedName("modifiedDate") val modifiedDate: String,
    @SerializedName("like") val like: Boolean,
    @SerializedName("scrap") val scrap: Boolean,
    @SerializedName("comments") val comments: ArrayList<Comments>,
    )

data class Comments(
    @SerializedName("username") val username: String,
    @SerializedName("content") val content: String,
    @SerializedName("modifiedDate") val modifiedDate: String
)

data class CommentResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: String
)