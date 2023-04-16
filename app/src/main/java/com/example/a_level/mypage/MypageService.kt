package com.example.a_level.mypage

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.GET

interface MypageService {
    @GET("users/mypage")
    fun getUserInfo(
    ): Call<UserInfoResponse>

    companion object{
        fun retrofitGetUserInfo():Call<UserInfoResponse>{
            return ApiClient.create(MypageService::class.java).getUserInfo()
        }
    }
}