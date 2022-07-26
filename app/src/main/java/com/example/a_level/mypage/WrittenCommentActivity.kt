package com.example.a_level.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.ActivityWrittencommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WrittenCommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrittencommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWrittencommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //툴바
        setSupportActionBar(binding.toolbar)
        val toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)
        toolbar.setDisplayHomeAsUpEnabled(true)

        // 작성 댓글 리스트 조회
        val writtenCommentList = arrayListOf(
            WrittenCommentData(R.drawable.ic_launcher_background, "블랑 너무 맛없어 우웩", "2022.06.21", 7)
        )

        val recyclerviewWrittenComment = binding.recyclerviewWrittenComment
        recyclerviewWrittenComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerviewWrittenComment.adapter = WrittenCommentAdapter(writtenCommentList)

        // 레트로핏
        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(MyPageService::class.java);

        service.getWrittenComment("1")?.enqueue(object : Callback<WrittenCommentDTO> {
            override fun onResponse(call: Call<WrittenCommentDTO>, response: Response<WrittenCommentDTO>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성고된 경우
                    var result: WrittenCommentDTO? = response.body()
                    Log.d("YMC", "onResponse 성공: " + result?.toString());
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<WrittenCommentDTO>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}