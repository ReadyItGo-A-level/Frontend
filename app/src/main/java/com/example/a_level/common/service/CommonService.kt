package com.example.a_level.common.service

import com.example.a_level.common.ApiClient
import com.example.a_level.common.model.request.DetailReview
import com.example.a_level.common.model.response.AlcoholDetailResponse
import com.example.a_level.common.model.response.DefaultResponse
import retrofit2.Call
import retrofit2.http.*

interface CommonService {
    @GET("alcohols/{id}")
    fun findAlcoholDetail(
        @Path("id") id: Long,
        @Query("userid") userId: Int
    ): Call<AlcoholDetailResponse>

    @POST("alcohols/{id}/scrap")
    fun requestScrap(
        @Path("id") id: Long,
        @Query("userid") userId: Long
    ): Call<DefaultResponse>

    @POST("alcohols/{id}/review")
    fun sendReview(
        @Path("id") id: Long,
        @Body request: DetailReview
    ): Call<DefaultResponse>

    @DELETE("alcohols/{id}/review/{review-id}")
    fun deleteReview(
        @Path("id") id: Integer,
        @Path("review-id") reviewId: Integer
    ): Call<DefaultResponse>

    companion object {
        fun findAlcoholDetail(
            id: Long,
            userId: Int
        ): Call<AlcoholDetailResponse> {
            return ApiClient.create(CommonService::class.java).findAlcoholDetail(id, userId)
        }

        fun requestScrap(
            id: Long,
            userId: Long
        ): Call<DefaultResponse> {
            return ApiClient.create(CommonService::class.java).requestScrap(id, userId)
        }

        fun sendReview(
            id: Long, request: DetailReview
        ): Call<DefaultResponse> {
            return ApiClient.create(CommonService::class.java).sendReview(id, request)
        }

        fun deleteReview(id: Integer, reviewId: Integer): Call<DefaultResponse> {
            return ApiClient.create(CommonService::class.java).deleteReview(id, reviewId)
        }
    }
}