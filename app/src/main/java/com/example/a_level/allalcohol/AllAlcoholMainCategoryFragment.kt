package com.example.a_level.allalcohol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.a_level.R
import com.example.a_level.databinding.FragmentAllalcoholmaincategoryBinding
import com.google.android.material.tabs.TabLayoutMediator


class AllAlcoholMainCategoryFragment : Fragment(R.layout.fragment_allalcoholmaincategory) {
    private lateinit var binding: FragmentAllalcoholmaincategoryBinding
    private lateinit var allAlcoholMainCategoryAdapter: AllAlcoholMainCategoryAdapter
    private lateinit var viewPager2: ViewPager2
    private val subCategoryList: ArrayList<String> = arrayListOf()
    private var category: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholmaincategoryBinding.inflate(layoutInflater)
        arguments?.let {
            category = it.getInt("category")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서브카테고리 List를 DB에서 불러온다
        // List를 이용해 서브카테고리 탭을 생성한다
        // 서브카테고리 탭을 변경할 때 마다 뷰페이저2를 이용해 서브카테고리 프래그먼트를 변경해준다
        loadSubCategoryList(category!!)
        allAlcoholMainCategoryAdapter = AllAlcoholMainCategoryAdapter(this)
        viewPager2 = binding.viewpager2AllalcoholmaincategoryFragmentcontainer
        viewPager2.adapter = allAlcoholMainCategoryAdapter
        setTabTitle()
    }

    private fun loadSubCategoryList(category: Int) {
        subCategoryList.add("테스트1")
        subCategoryList.add("테스트2")
        subCategoryList.add("테스트3")
    }

    private fun setTabTitle() {
        val tabLayout = binding.tablayoutAllalcoholmaincategorySubcategory
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = subCategoryList[position]
        }.attach()
    }


    inner class AllAlcoholMainCategoryAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return subCategoryList.size
        }

        override fun createFragment(position: Int): Fragment {
            val fragment = AllAlcoholSubCategoryFragment()
            fragment.arguments = Bundle().apply {
                putInt("category", category!!)
                putInt("position", position) //DB 연결 후 서브클래스 정보로 변경 필요
            }
            return fragment
        }

    }
}