package com.example.a_level.feed.model.response

import com.google.gson.annotations.SerializedName

data class Comment(
    val username: String?,
    val content: String?,
    @SerializedName("modified_date")
    val modifiedDate: String?
)
