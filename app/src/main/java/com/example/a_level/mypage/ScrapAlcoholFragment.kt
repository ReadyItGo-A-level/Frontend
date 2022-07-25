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
            ScrapAlcoholData(R.drawable.allalcoholsubcategory_item_testimage, "이슬톡톡", "2500원", "350ml", "3%"),
            ScrapAlcoholData(R.drawable.allalcoholsubcategory_item_testimage, "이슬톡톡", "2500원", "350ml", "3%"),
            ScrapAlcoholData(R.drawable.allalcoholsubcategory_item_testimage, "이슬톡톡", "2500원", "350ml", "3%"),
            ScrapAlcoholData(R.drawable.allalcoholsubcategory_item_testimage, "이슬톡톡", "2500원", "350ml", "3%"),
            ScrapAlcoholData(R.drawable.allalcoholsubcategory_item_testimage, "이슬톡톡", "2500원", "350ml", "3%")
        )

        val rv_salcohol = binding.rvSalcohol
        val gridLayoutManager = GridLayoutManager(context, 2)
        rv_salcohol.layoutManager = gridLayoutManager

        rv_salcohol.adapter = ScrapAlcoholAdapter(scrapalcoholList)
        return binding.root
    }
}