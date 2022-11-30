package com.example.a_level.login

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @GET("users/login")
//    @Headers("accept: application/json",
//        "content-type: application/json"
//    )
    fun getLogin(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<LoginResponse>

    companion object{
        fun getRetrofitLogin(email: String, password: String): Call<LoginResponse>{
            return ApiClient.create(LoginService::class.java).getLogin(email, password)
        }
    }
}