package com.example.a_level.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.a_level.R
import com.example.a_level.allalcohol.AllAlcoholFragment
import com.example.a_level.databinding.ActivityMainBinding
import com.example.a_level.feed.FeedFragment
import com.example.a_level.mypage.MyPageFragment
import com.example.a_level.recommend.RecommendFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initBottomNavigation()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout_main_fragment, fragment).commit()
    }

    private fun initBottomNavigation() {
        binding.constraintlayoutMainBnav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.recommend -> {
                        replaceFragment(RecommendFragment())
                    }
                    R.id.all_alcohol -> {
                        replaceFragment(AllAlcoholFragment())
                    }
                    R.id.feed -> {
                        replaceFragment(FeedFragment())
                    }
                    R.id.my_page -> {
                        replaceFragment(MyPageFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.recommend
        }
    }

}