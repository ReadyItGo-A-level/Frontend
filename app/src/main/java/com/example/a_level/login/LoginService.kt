package com.example.a_level.login

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @POST("login")
    fun postLogin(
        @Body jsonparams: LoginRequest
    ): Call<LoginResponse>

    companion object{
        fun getRetrofitLogin(jsonparams: LoginRequest): Call<LoginResponse>{
            return ApiClient.create(LoginService::class.java).postLogin(jsonparams)
        }
    }
}