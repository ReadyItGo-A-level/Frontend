package com.example.a_level.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.FragmentScrapalcoholBinding

class ScrapAlcoholFragment : Fragment() {
    private lateinit var binding: FragmentScrapalcoholBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScrapalcoholBinding.inflate(layoutInflater)

        val scrapalcoholList = arrayListOf(
            ScrapAlcoholData(R.drawable.all_alcohol_image, "소비뇽 블랑", "24800원", "750ml", "13%"),
            ScrapAlcoholData(R.drawable.all_alcohol_image, "엡솔루트 보드카", "17000원", "375ml", "40%"),
            ScrapAlcoholData(R.drawable.all_alcohol_image, "보야, 피노누아", "38000원", "750ml", "13%"),
        )

        val recyclerviewScrapAlcohol = binding.recyclerviewScrapAlcohol
        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerviewScrapAlcohol.layoutManager = gridLayoutManager

        val scrapAlcoholList_empty = binding.linearlayoutScrapalcoholNodata
        if (ScrapAlcoholAdapter(scrapalcoholList).itemCount == 0) {
            scrapAlcoholList_empty.visibility = View.VISIBLE
        } else {
            scrapAlcoholList_empty.visibility = View.INVISIBLE
            recyclerviewScrapAlcohol.adapter = ScrapAlcoholAdapter(scrapalcoholList)
        }
        return binding.root
    }
}