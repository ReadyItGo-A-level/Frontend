package com.example.a_level.allalcohol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.R
import com.example.a_level.databinding.FragmentAllalcoholBinding


class AllAlcoholFragment : Fragment() {
    private lateinit var binding: FragmentAllalcoholBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholBinding.inflate(layoutInflater)
        return binding.root
    }
}