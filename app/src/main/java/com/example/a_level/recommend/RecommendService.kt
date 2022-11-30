package com.example.a_level.recommend

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendService {
    @GET("/recommendations/preference")
    fun getRecommend(
        @Query("userid") userid: Long
    ): Call<RecommendResponse>

    @GET("recommendations/alcohol")
    fun getRecommendAlcohol(
        @Query("userid") userid: Long
    ):Call<RecommendAlcoholResponse>

    @GET("/recommendations/post")
    fun getRecommendPost(
        @Query("userid") userid: Long
    ):Call<RecommendPostResponse>

    @GET("/recommendations/top-post")
    fun getRecommendTopPost():Call<RecommendTopPostResponse>

    companion object{
        fun getRetrofitRecommend(userid: Long): Call<RecommendResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommend(userid)
        }
        fun getRetrofitRecommendAlcohol(userid: Long): Call<RecommendAlcoholResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommendAlcohol(userid)
        }
        fun getRetrofitRecommendPost(userid: Long): Call<RecommendPostResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommendPost(userid)
        }
        fun getRetrofitRecommendTopPost(): Call<RecommendTopPostResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommendTopPost()
        }
    }
}
