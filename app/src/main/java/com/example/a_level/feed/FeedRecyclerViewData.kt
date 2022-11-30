package com.example.a_level.feed

import android.content.Context

data class FeedRecyclerViewData(
    val title: String,
    val content: String,
    val likeCount: Int,
    val scrapCount: Int,
    val commentCount: Int,
    val viewType: Int
)