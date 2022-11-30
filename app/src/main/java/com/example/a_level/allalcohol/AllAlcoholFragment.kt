package com.example.a_level.allalcohol

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_level.R
import com.example.a_level.common.Const
import com.example.a_level.databinding.FragmentAllalcoholBinding
import com.google.android.material.tabs.TabLayout

class AllAlcoholFragment : Fragment(R.layout.fragment_allalcohol) {
    private lateinit var binding: FragmentAllalcoholBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .add(R.id.framelayout_allalcohol_fragmentcontainer, AllAlcoholSubCategoryFragment())
            .commit()

        initListener()
        setTabLayout()
    }

    private fun initListener() {
        binding.textviewAllalcoholSearchbar.setOnClickListener {
            startActivity(Intent(requireContext(), AllAlcoholSearchActivity::class.java))
        }
    }

    private fun setTabLayout() {
        var tabLayout = binding.tablayoutAllalcoholMaincategory
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    Const.ALL -> {
                        replaceFragment(AllAlcoholSubCategoryFragment(), Const.ALL)
                    }
                    Const.BEER -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), Const.BEER)
                    }
                    Const.WINE -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), Const.WINE)
                    }
                    Const.TRADITIONAL -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), Const.TRADITIONAL)
                    }
                    Const.LIQUOR -> {
                        replaceFragment(AllAlcoholMainCategoryFragment(), Const.LIQUOR)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun replaceFragment(fragment: Fragment, type: Int) {
        val bundle = Bundle()
        bundle.putInt("type", type)
        if (type == Const.ALL){
            bundle.putString("category", "")
        }
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .replace(R.id.framelayout_allalcohol_fragmentcontainer, fragment).commit()
    }

}