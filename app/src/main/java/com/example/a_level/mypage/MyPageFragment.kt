package com.example.a_level.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(layoutInflater)

        val myaccount = binding.myaccount
        myaccount.setOnClickListener {
            activity?.let {
                val intent = Intent(context, MyAccountActivity::class.java)
                startActivity(intent)
            }
        }

        val scrap = binding.scrap
        scrap.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ScrapActivity::class.java)
                startActivity(intent)
            }
        }

        val post = binding.post
        post.setOnClickListener {
            activity?.let {
                val intent = Intent(context, WrittenPostActivity::class.java)
                startActivity(intent)
            }
        }

        val comment = binding.comment
        comment.setOnClickListener {
            activity?.let {
                val intent = Intent(context, WrittenCommentActivity::class.java)
                startActivity(intent)
            }
        }

        val setting = binding.setting
        setting.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SettingActivity::class.java)
                startActivity(intent)
            }
        }

        val addalcohol = binding.addalcohol
        addalcohol.setOnClickListener {
            activity?.let {
                val intent = Intent(context, AddAlcoholActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}