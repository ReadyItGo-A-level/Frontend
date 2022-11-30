package com.example.a_level.common.model.response

import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.feed.model.response.Post
import com.google.gson.annotations.SerializedName

data class AlcoholDetailResponse(
    val status: Int,
    val message: String,
    val data: Data
)

data class Data(
    @SerializedName("alcohol")
    val alcohol: Alcohol,
    @SerializedName("post")
    val posts: List<Post>,
    @SerializedName("scrap")
    val scrapStatus: Boolean,
    @SerializedName("recommendationPost")
    val recommendationPosts: List<Post>,
    @SerializedName("review")
    val reviews: List<Review>
)

