package com.example.a_level.allalcohol.service

import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.allalcohol.model.response.AllAlcoholResponse
import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AllAlcoholService {
    @GET("/alcohols")
    suspend fun findAlcohol(
        @Query("type") type: String,
        @Query("category") category: String,
        @Query("page") page: Int
    ): Response<AllAlcoholResponse>

    @GET("/alcohol/search")
    fun searchAlcohol(
        @Query("keyword") keyword: String
    ): Call<ArrayList<Alcohol>>

    companion object {
        suspend fun findAlcohol(
            type: String,
            category: String,
            page: Int
        ): Response<AllAlcoholResponse> {
            return ApiClient.create(AllAlcoholService::class.java)
                .findAlcohol(type, category, page)
        }

        fun searchAlcohol(
            keyword: String
        ): Call<ArrayList<Alcohol>> {
            return ApiClient.create(AllAlcoholService::class.java).searchAlcohol(keyword)
        }
    }
}