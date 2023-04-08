package com.example.a_level.login

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.*

interface SignUpService {
        @POST("signup")
        fun postSignUp(
            @Body jsonParams:SignupRequest
        ): Call<SignUpResponse>

        @GET("users/check/{username}")
        fun getUsername(
            @Path("username") username: String,
        ): Call<CheckUserResponse>

        @POST("users/check/email")
        fun postEmailCheck(
            @Query("email") email: String,
        ): Call<CheckEmailResponse>

        @GET("users/check/email")
        fun getEmailCheck(
            @Query("email") email: String,
            @Query("token") token: String
        ): Call<CheckEmailResponse>

    companion object{
        fun getRetrofitSignUp(jsonParams:SignupRequest): Call<SignUpResponse> {
            return ApiClient.create(SignUpService::class.java).postSignUp(jsonParams)
        }

        fun getRetrofitUsername(username: String): Call<CheckUserResponse>{
            return ApiClient.create(SignUpService::class.java).getUsername(username)
        }

        fun getRetrofitPostEmail(email: String): Call<CheckEmailResponse> {
            return ApiClient.create(SignUpService::class.java).postEmailCheck(email)
        }

        fun getRetrofitEmailCheck(email: String, token: String): Call<CheckEmailResponse> {
            return ApiClient.create(SignUpService::class.java).getEmailCheck(email, token)
        }
    }
}