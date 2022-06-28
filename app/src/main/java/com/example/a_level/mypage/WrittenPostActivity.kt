package com.example.a_level.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.ActivityWrittenpostBinding

class WrittenPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrittenpostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWrittenpostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        val writtenpostList = arrayListOf(
            WrittenPostData(R.drawable.ic_launcher_background, "오늘은 맥주가 땡기는 걸", "이슬톡톡 어때", "2022.06.21", 7)
        )

        val rv_wpost = binding.rvWpost
        rv_wpost.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv_wpost.adapter = WrittenPostAdapter(writtenpostList)
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