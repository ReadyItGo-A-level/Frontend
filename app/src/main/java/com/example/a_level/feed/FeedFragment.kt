package com.example.a_level.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(layoutInflater)
        return binding.root
    }
}