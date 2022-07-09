package com.example.a_level.mypage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageService {
    //GET 예제
    @GET("posts/{page}")
    fun getWrittenPost(@Path("id") id: String): Call<WrittenPostDTO>

    @GET("posts/{page}")
    fun getWrittenComment(@Path("id") id: String): Call<WrittenCommentDTO>


//    @GET("posts/1")
//    fun getStudent(@Query("school_id") schoolId: Int,
//                   @Query("grade") grade: Int,
//                   @Query("classroom") classroom: Int): Call<ExampleResponse>
//
//
//    //POST 예제
//    @FormUrlEncoded
//    @POST("posts")
//    fun getContactsObject(@Field("idx") idx: String): Call<JsonObject>
}