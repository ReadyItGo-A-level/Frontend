package com.example.a_level.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendBinding.inflate(layoutInflater)
        return binding.root
    }
}