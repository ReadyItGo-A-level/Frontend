package com.example.a_level.keyword

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a_level.App
import com.example.a_level.common.MainActivity
import com.example.a_level.databinding.ActivityUserStyleBinding
import com.example.a_level.recommend.*
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class UserStyleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserStyleBinding
    private var volumeNum=1
    private var volumne=listOf("낮은, 보통, 높은")
    private var sugarNum=1
    private var flavorList= arrayListOf<String>()
    private lateinit var recommendAlcoholResponse: RecommendAlcoholResponse
    private lateinit var recommendPostResponse: RecommendPostResponse
    private lateinit var recommendTopPostResponse: RecommendTopPostResponse
    private lateinit var alcoholList: ArrayList<RecommendAlcohol>
    private lateinit var postList: ArrayList<RecommendPost>
    private lateinit var topPostList: ArrayList<RecommendTopPost>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserStyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var drink=intent.getStringExtra("drink")!!
        Log.e("drink", "$drink")

        binding.sliderStylePercent.addOnSliderTouchListener(object: Slider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                Log.e("percent","${slider.value}")
                volumeNum=slider.value.toInt()
            }
        })

        binding.sliderStyleSugar.addOnSliderTouchListener(object: Slider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                Log.e("sugar","${slider.value}")
                sugarNum=slider.value.toInt()
            }
        })

        binding.sliderStyleMoney.addOnSliderTouchListener(object: RangeSlider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: RangeSlider) {
                binding.textviewStyleMoneystart.setText(slider.values[0].toInt().toString())
                binding.textviewStyleMoneyend.text=slider.values[1].toInt().toString()
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                binding.textviewStyleMoneystart.text=slider.values[0].toInt().toString()
                binding.textviewStyleMoneyend.text=slider.values[1].toInt().toString()
            }
        })

        binding.buttonStyleNext.setOnClickListener{
            flavorList?.clear()
            checkChip()

            var flavor=flavorList.joinToString(separator = ",")
            Log.e("flavor: ", "$flavor")
            var price=(binding.textviewStyleMoneystart.text.toString().toLong()*10000).toString()+','+(binding.textviewStyleMoneyend.text.toString().toLong()*10000).toString()
            Log.e("price", "$price")
            var request=PreferenceRequest(drink, volumeNum.toString(), sugarNum.toInt(), flavor, price)

            PreferenceService.getRetrofitPreference(request).enqueue(object: Callback<PreferenceResponse> {
                override fun onResponse(
                    call: Call<PreferenceResponse>,
                    response: Response<PreferenceResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.e("술 취향등록", response.toString())
                        Log.e("술 취향등록", response.body().toString())

//                        recommendAlcoholApi(App.prefs.userid!!)
//                        recommendPostApi(App.prefs.userid!!)
//                        recommendTopPostApi()
//
//                        val gson=GsonBuilder().create()
//                        val Alcohol=RecommendAlcohol(recommendAlcoholResponse.data)
//                        val post=RecommendPost(recommendPostResponse.data)
//                        val TopPost=RecommendTopPost(recommendTopPostResponse.data)
//                        val groupListType: Type=object: TypeToken<ArrayList<RecommendAlcohol?>?>(){}.type
////                        App.prefs.recommendPost
//                        val jsonArray: JSONArray
//                        val strList=gson.toJson(post, )

//                        var recommend = Recommend(recommendResponse.data.alcohol, recommendResponse.data.post, recommendResponse.data.topPost)
                        val intent = Intent(this@UserStyleActivity, MainActivity::class.java)
//                        intent.putExtra("recommendData", recommend)
                        startActivity(intent)
                    }else{
                        try {
                            val body = response.errorBody()!!.string()

                            Log.e(ContentValues.TAG, "body : $body")
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<PreferenceResponse>, t: Throwable) {
                    Log.d("TAG", "실패원인: {$t}")
                }
            })
        }
    }

    private fun checkChip(){
        if(binding.chipStyleCarbon.isChecked)
            flavorList.add("탄산")
        if(binding.chipStyleFruit.isChecked)
            flavorList.add("과일향")
        if(binding.chipStyleSweetSour.isChecked)
            flavorList.add("새콤달콤")
        if(binding.chipStyleFlower.isChecked)
            flavorList.add("꽃향")
        if(binding.chipStyleRefresh.isChecked)
            flavorList.add("청량감")
        if(binding.chipStyleSoft.isChecked)
            flavorList.add("부드러움")
        if(binding.chipStyleBitter.isChecked)
            flavorList.add("씁쓸함")
        if(binding.chipStyleRose.isChecked)
            flavorList.add("로제")
        if(binding.chipStyleHeavy.isChecked)
            flavorList.add("묵직함")
        if(binding.chipStyleLight.isChecked)
            flavorList.add("가벼움")
    }

    fun recommendAlcoholApi(userid: Long){
        RecommendService.getRetrofitRecommendAlcohol().enqueue(object: Callback<RecommendAlcoholResponse>{
            override fun onResponse(
                call: Call<RecommendAlcoholResponse>,
                response: Response<RecommendAlcoholResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())

                    recommendAlcoholResponse= response.body()!!
                }else{
                    try {
                        val body = response.errorBody()!!.string()

                        Log.e(ContentValues.TAG, "body : $body")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RecommendAlcoholResponse>, t: Throwable) {
                Log.d("TAG", "실패원인: {$t}")
            }

        })
    }

    fun recommendPostApi(userid: Long){
        RecommendService.getRetrofitRecommendPost().enqueue(object: Callback<RecommendPostResponse>{
            override fun onResponse(
                call: Call<RecommendPostResponse>,
                response: Response<RecommendPostResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())

                    recommendPostResponse = response.body()!!
                }else{
                    try {
                        val body = response.errorBody()!!.string()

                        Log.e(ContentValues.TAG, "body : $body")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RecommendPostResponse>, t: Throwable) {
                Log.d("TAG", "실패원인: {$t}")
            }

        })
    }

    fun recommendTopPostApi(){
        RecommendService.getRetrofitRecommendTopPost().enqueue(object: Callback<RecommendTopPostResponse>{
            override fun onResponse(
                call: Call<RecommendTopPostResponse>,
                response: Response<RecommendTopPostResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())

                    recommendTopPostResponse= response.body()!!
                }else{
                    try {
                        val body = response.errorBody()!!.string()

                        Log.e(ContentValues.TAG, "body : $body")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RecommendTopPostResponse>, t: Throwable) {
                Log.d("TAG", "실패원인: {$t}")
            }

        })
    }
}