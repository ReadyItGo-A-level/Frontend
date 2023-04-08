package com.example.a_level.recommend

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendService {
    @GET("recommendations/preference")
    fun getRecommend(
    ): Call<RecommendResponse>

    @GET("recommendations/alcohol")
    fun getRecommendAlcohol(
    ):Call<RecommendAlcoholResponse>

    @GET("recommendations/post")
    fun getRecommendPost(
    ):Call<RecommendPostResponse>

    @GET("recommendations/top-post")
    fun getRecommendTopPost():Call<RecommendTopPostResponse>

    companion object{
        fun getRetrofitRecommend(): Call<RecommendResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommend()
        }
        fun getRetrofitRecommendAlcohol(): Call<RecommendAlcoholResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommendAlcohol()
        }
        fun getRetrofitRecommendPost(): Call<RecommendPostResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommendPost()
        }
        fun getRetrofitRecommendTopPost(): Call<RecommendTopPostResponse>{
            return ApiClient.create(RecommendService::class.java).getRecommendTopPost()
        }
    }
}
