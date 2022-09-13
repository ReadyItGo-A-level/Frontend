package com.example.a_level.api

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.*

interface SignUpService {
//        @FormUrlEncoded
        @POST("users/signup")
//        @Headers("accept: application/json",
//            "content-type: application/json")
        fun postSignUp(
//            @Body jsonparams: SignUpRequest
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("username") username: String
        ): Call<SignUpResponse>

        @GET("users/check/{username}")
    //    @Headers("accept: application/json",
    //        "content-type: application/json"
    //    )
        fun getUsername(
            @Path("username") username: String,
        ): Call<CheckUserResponse>

        @GET("users/check/email")
    //    @Headers("accept: application/json",
    //        "content-type: application/json"
    //    )
        fun getEmailCheck(
            @Query("email") email: String,
        ): Call<CheckEmailResponse>


    companion object{
        fun getRetrofitSignUp(email: String, password: String, username: String): Call<SignUpResponse> {
            return ApiClient.create(SignUpService::class.java).postSignUp(email, password, username)
        }

        fun getRetrofitUsername(username: String): Call<CheckUserResponse>{
            return ApiClient.create(SignUpService::class.java).getUsername(username)
        }

        fun getRetrofitEmailCheck(email: String): Call<CheckEmailResponse> {
            return ApiClient.create(SignUpService::class.java).getEmailCheck(email)
        }
    }
}