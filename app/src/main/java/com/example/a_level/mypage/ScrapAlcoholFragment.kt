package com.example.a_level.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.databinding.FragmentScrapalcoholBinding

class ScrapAlcoholFragment : Fragment() {
    private lateinit var binding: FragmentScrapalcoholBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScrapalcoholBinding.inflate(layoutInflater)
        return binding.root
    }
}