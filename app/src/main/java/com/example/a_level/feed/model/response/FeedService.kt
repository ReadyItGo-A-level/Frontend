package com.example.a_level.feed.model.response

import com.example.a_level.common.ApiClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface FeedService {
    @Multipart
    @POST("posts")
    fun postFeed(
        @Part file: MultipartBody.Part,
        @Part("dto") dto: RequestBody
    ): Call<FeedResponse>

    @GET("posts")
    fun getFeedList(
    ):Call<FeedListResponse>

    @GET("posts/{id}")
    fun getDetailFeed(
        @Path("id") id:Long
    ):Call<FeedDetailResponse>

    @POST("posts/{id}/comment")
    fun postComment(
        @Path("id") id: Long,
        @Body jsonParams:CommentRequest
    ):Call<CommentResponse>

    companion object{
        fun retrofitPostFeed(file: MultipartBody.Part,dto: RequestBody):Call<FeedResponse>{
            return ApiClient.create(FeedService::class.java).postFeed(file,dto)
        }
        fun retrofitGetFeedList():Call<FeedListResponse>{
            return ApiClient.create(FeedService::class.java).getFeedList()
        }
        fun retrofitGetFeedDetail(id: Long):Call<FeedDetailResponse>{
            return ApiClient.create(FeedService::class.java).getDetailFeed(id)
        }
        fun retrofitPostComment(id: Long,jsonParams: CommentRequest):Call<CommentResponse>{
            return ApiClient.create(FeedService::class.java).postComment(id,jsonParams)
        }
    }
}