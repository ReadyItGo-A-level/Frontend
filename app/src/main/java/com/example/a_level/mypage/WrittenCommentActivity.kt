package com.example.a_level.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.ActivityWrittencommentBinding

class WrittenCommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWrittencommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWrittencommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        val writtencommentList = arrayListOf(
            WrittenCommentData(R.drawable.ic_launcher_background, "블랑 너무 맛없어 우웩", "2022.06.21", 7)
        )

        val rv_wcomment = binding.rvWcomment
        rv_wcomment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv_wcomment.adapter = WrittenCommentAdapter(writtencommentList)
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