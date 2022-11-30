package com.example.a_level.common

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.a_level.R
import com.example.a_level.common.model.request.DetailReview
import com.example.a_level.common.model.response.DefaultResponse
import com.example.a_level.common.service.CommonService
import com.example.a_level.databinding.ActivityAlcoholdetailwritereviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlcoholDetailWriteReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlcoholdetailwritereviewBinding
    var alcoholId: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlcoholdetailwritereviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //툴바 적용하기
        initActionBar()
        initUi()
    }

    private fun initUi() {
        val extras = intent.extras
        if (extras != null){
            binding.textviewAlcoholdetailwritereviewName.text = extras.getString("name")
            binding.textviewAlcoholdetailwritereviewInformation.text = extras.getString("information")
            alcoholId = extras.getLong("alcoholId")
        }
    }

    private fun initActionBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.title = "한 줄 리뷰"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_write, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_write_submit -> {
                val request = DetailReview(1, binding.edittextAlcoholdetailwritereviewInput.text.toString())
                CommonService.sendReview(alcoholId, request).enqueue(object : Callback<DefaultResponse>{
                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        if (response.code() == 200){
                            Toast.makeText(applicationContext, "작성했습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Log.e("AlcoholDetailWriteReview", "response code : ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Log.e("AlcoholDetailWriteReview", "error : ${t.localizedMessage}")
                    }
                })
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}