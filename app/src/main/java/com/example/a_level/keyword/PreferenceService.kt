package com.example.a_level.keyword

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PreferenceService {
    @POST("/users/preference")
    fun postPreference(
        @Body jsonParams: PreferenceRequest
    ): Call<PreferenceResponse>

    companion object{
        fun getRetrofitPreference(preferenceRequest: PreferenceRequest): Call<PreferenceResponse>{
            return ApiClient.create(PreferenceService::class.java).postPreference(preferenceRequest)
        }
    }
}