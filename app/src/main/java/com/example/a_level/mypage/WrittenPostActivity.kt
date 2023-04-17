package com.example.a_level.mypage

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        val toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)
        toolbar.setDisplayHomeAsUpEnabled(true)

        val writtenPostList = arrayListOf(
            WrittenPostData(
                R.drawable.mypage_post_noimage,
                "오늘은 맥주가 땡기는 걸",
                "이슬톡톡 어때",
                "2022.06.21",
                7
            )
        )

        val recyclerviewWrittenPost = binding.recyclerviewWrittenpost
        recyclerviewWrittenPost.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val writtenPostList_empty = binding.linearlayoutWrittenpostNodata
        if (WrittenPostAdapter(writtenPostList).itemCount == 0) {
            writtenPostList_empty.visibility = View.VISIBLE
        } else {
            writtenPostList_empty.visibility = View.INVISIBLE
            recyclerviewWrittenPost.adapter = WrittenPostAdapter(writtenPostList)
        }
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