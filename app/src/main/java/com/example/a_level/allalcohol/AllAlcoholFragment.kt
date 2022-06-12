package com.example.a_level.allalcohol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.R
import com.example.a_level.databinding.FragmentAllalcoholBinding
import com.google.android.material.tabs.TabLayout

class AllAlcoholFragment : Fragment(R.layout.fragment_allalcohol) {
    private lateinit var binding: FragmentAllalcoholBinding
    private val ALL = 0
    private val BEER = 1
    private val WINE = 2
    private val TRADITIONAL = 3
    private val LIQUOR = 4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholBinding.inflate(layoutInflater)
        setTabLayout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .add(R.id.framelayout_allalcohol_fragmentcontainer, AllAlcoholSubCategoryFragment()).commit()
    }

    private fun setTabLayout() {
        var tabLayout = binding.tablayoutAllalcoholMaincategory
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val bundle = Bundle()
                when (tab!!.position) {
                    ALL -> {
                        replaceFragment(AllAlcoholSubCategoryFragment(), ALL)
                    }
                    BEER -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), BEER)
                    }
                    WINE -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), WINE)
                    }
                    TRADITIONAL -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), TRADITIONAL)
                    }
                    LIQUOR -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), LIQUOR)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun replaceFragment(fragment: Fragment, category: Int) {
        val bundle = Bundle()
        bundle.putInt("category", category)
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .replace(R.id.framelayout_allalcohol_fragmentcontainer, fragment).commit()
    }

}