package com.example.a_level.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.example.a_level.databinding.ActivityScrapBinding
import com.example.a_level.databinding.ActivityWrittenpostBinding
import com.google.android.material.tabs.TabLayoutMediator

class ScrapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScrapBinding
    val tabTextList = arrayListOf("술", "게시글")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        val vp = binding.viewpager
        vp.adapter = TabViewAdapter(this)
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 뷰페이저와 탭레이아웃을 붙임
        TabLayoutMediator(binding.tab, vp) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
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