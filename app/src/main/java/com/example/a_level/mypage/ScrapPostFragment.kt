package com.example.a_level.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.FragmentScrappostBinding

class ScrapPostFragment : Fragment() {
    private lateinit var binding: FragmentScrappostBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScrappostBinding.inflate(layoutInflater)

        val scrappostList = arrayListOf(
            ScrapPostData(R.drawable.mypage_post_noimage, "오늘은 맥주가 땡기는 걸", "이슬톡톡 어때", "2022.06.21", 7)
        )

        val recyclerviewScrapPost = binding.recyclerviewScrapPost
        recyclerviewScrapPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val scrapPostList_empty = binding.linearlayoutScrappostNodata
        if (ScrapPostAdapter(scrappostList).itemCount == 0) {
            scrapPostList_empty.visibility = View.VISIBLE
        } else {
            scrapPostList_empty.visibility = View.INVISIBLE
            recyclerviewScrapPost.adapter = ScrapPostAdapter(scrappostList)
        }

        return binding.root
    }
}