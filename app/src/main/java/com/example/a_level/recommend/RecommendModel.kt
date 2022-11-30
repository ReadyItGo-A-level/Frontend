package com.example.a_level.recommend

import android.media.Image
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecommendResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data,
){
    data class Data(
        @SerializedName("alcohol") val alcohol: Alcohol,
        @SerializedName("post") val post: ArrayList<Post>,
        @SerializedName("topPost") val topPost: ArrayList<Post>
    ){
        data class Alcohol(
            @SerializedName("alocohols") val alcohols: ArrayList<AlcoholData>,
            @SerializedName("sool") val sool: ArrayList<AlcoholData>,
            @SerializedName("liquor") val liquor: ArrayList<AlcoholData>,
            @SerializedName("wine") val wine: ArrayList<AlcoholData>,
            @SerializedName("beer") val beer: ArrayList<AlcoholData>,
        ){
            data class AlcoholData(
                @SerializedName("image") val image: String,
                @SerializedName("name") val name: String
            )
        }

        data class Post(
            @SerializedName("id") val id: Long,
            @SerializedName("title") val title: String,
            @SerializedName("content") val content: String,
            @SerializedName("image") val image: String,
            @SerializedName("commentCount") val commentCount: Long,
            @SerializedName("scrapCount") val scrapCount: Long,
            @SerializedName("likeCount") val likeCount: Long
        )
    }
}

data class RecommendAlcoholResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data:Data,
){
    data class Data(
        @SerializedName("alocohols") val alcohol: ArrayList<Alcohol>,
        @SerializedName("sool") val sool: ArrayList<Alcohol>,
        @SerializedName("liqor") val liqor: ArrayList<Alcohol>,
        @SerializedName("wine") val wine: ArrayList<Alcohol>,
        @SerializedName("beer") val beer: ArrayList<Alcohol>,
    )
    {
        data class Alcohol(
            @SerializedName("image") val image: String,
            @SerializedName("name") val name: String
        )
    }
}

data class RecommendPostResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<Data>
){
    data class Data(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("image") val image: String,
        @SerializedName("commentCount") val commentCount: Int,
        @SerializedName("scrapCount") val scrapCount: Int,
        @SerializedName("likeCount") val likeCount: Int
    )
}

data class RecommendTopPostResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArrayList<Data>
){
    data class Data(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("image") val image: String,
        @SerializedName("commentCount") val commentCount: Int,
        @SerializedName("scrapCount") val scrapCount: Int,
        @SerializedName("likeCount") val likeCount: Int
    )
}

data class RecommendAlcohol(
    val data: RecommendAlcoholResponse.Data,
): Serializable{
    data class Data(
        val image: String,
        val name: String
    )
}

data class RecommendPost(
    val data: ArrayList<RecommendPostResponse.Data>,
):Serializable{
    data class Data(
        val id: Int,
        val title: String,
        val content: String,
        val image: String,
        val commentCount: Int,
        val scrapCount: Int,
        val likeCount: Int
    )
}

data class RecommendTopPost(
    val data: ArrayList<RecommendTopPostResponse.Data>,
):Serializable{
    data class Data(
        val id: Int,
        val title: String,
        val content: String,
        val image: String,
        val commentCount: Int,
        val scrapCount: Int,
        val likeCount: Int
    )
}

//data class Recommend(
//    val alcohol: List<RecommendResponse.Data.Alcohol>,
//    val post: List<RecommendResponse.Data.Post>,
//    val topPost: List<RecommendResponse.Data.TopPost>
//): Serializable {
//    data class Alcohol(
//        val image: String,
//        val name: String
//    )
//
//    data class Post(
//        val id: Int,
//        val title: String,
//        val content: String,
//        val image: String,
//        val commentCount: Int,
//        val scrapCount: Int,
//        val likeCount: Int
//    )
//
//    data class TopPost(
//        val id: Int,
//        val title: String,
//        val content: String,
//        val image: String,
//        val commentCount: Int,
//        val scrapCount: Int,
//        val likeCount: Int
//    )
//}