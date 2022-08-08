package com.example.a_level.common

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.a_level.databinding.ActivityAlcoholdetailwritereviewBinding

class AlcoholDetailWriteReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlcoholdetailwritereviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlcoholdetailwritereviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //툴바 적용하기
        initActionBar()
    }

    private fun initActionBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.title = "한줄리뷰"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}