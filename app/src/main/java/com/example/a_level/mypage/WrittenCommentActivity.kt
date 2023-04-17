package com.example.a_level.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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

        // 작성 댓글 리스트 조회
        val writtenCommentList = arrayListOf(
            WrittenCommentData(
                R.drawable.mypage_comment,
                "블랑 너무 맛없어 우웩",
                "2022.06.21"
            )
        )

        val recyclerviewWrittenComment = binding.recyclerviewWrittenComment
        recyclerviewWrittenComment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val writtenCommentList_empty = binding.linearlayoutWrittencommentNodata
        if (WrittenCommentAdapter(writtenCommentList).itemCount == 0) {
            writtenCommentList_empty.visibility = View.VISIBLE
        } else {
            writtenCommentList_empty.visibility = View.INVISIBLE
            recyclerviewWrittenComment.adapter = WrittenCommentAdapter(writtenCommentList)
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