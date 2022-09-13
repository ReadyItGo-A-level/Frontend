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
            ScrapPostData(R.drawable.ic_launcher_background, "오늘은 맥주가 땡기는 걸", "이슬톡톡 어때", "2022.06.21", 7)
        )

        val rv_spost = binding.rvSpost
        rv_spost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rv_spost.adapter = ScrapPostAdapter(scrappostList)
        return binding.root
    }
}