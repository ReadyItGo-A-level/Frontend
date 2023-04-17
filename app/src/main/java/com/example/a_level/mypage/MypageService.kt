package com.example.a_level.mypage

import com.example.a_level.common.ApiClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MypageService {
    @GET("users/mypage")
    fun getUserInfo(
    ): Call<UserInfoResponse>

    @GET("posts/{page}")
    fun getWrittenPost(@Path("id") id: String): Call<WrittenPostDTO>

    @GET("posts/{page}")
    fun getWrittenComment(@Path("id") id: String): Call<WrittenCommentDTO>

    companion object{
        fun retrofitGetUserInfo():Call<UserInfoResponse>{
            return ApiClient.create(MypageService::class.java).getUserInfo()
        }
    }
}