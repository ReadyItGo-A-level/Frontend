package com.example.a_level.keyword

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.example.a_level.common.MainActivity
import com.example.a_level.databinding.ActivityUserStyleBinding
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class UserStyleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserStyleBinding
    private var volumeNum=1
    private var volumne=listOf("낮은, 보통, 높은")
    private var sugarNum=1
    private var flavorList= arrayListOf<String>()

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
            var price=binding.textviewStyleMoneystart.text.toString()+','+binding.textviewStyleMoneyend.text.toString()
            Log.e("price", "$price")
            var request=PreferenceRequest(1, drink, volumeNum.toString(), sugarNum, flavor, price)
            PreferenceService.getRetrofitPreference(request).enqueue(object: Callback<PreferenceResponse> {
                override fun onResponse(
                    call: Call<PreferenceResponse>,
                    response: Response<PreferenceResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.d("log", response.toString())
                        Log.d("log", response.body().toString())

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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
}