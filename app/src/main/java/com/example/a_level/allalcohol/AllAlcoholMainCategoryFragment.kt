package com.example.a_level.allalcohol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.a_level.R
import com.example.a_level.common.Const
import com.example.a_level.databinding.FragmentAllalcoholmaincategoryBinding
import com.google.android.material.tabs.TabLayoutMediator


class AllAlcoholMainCategoryFragment : Fragment(R.layout.fragment_allalcoholmaincategory) {
    private lateinit var binding: FragmentAllalcoholmaincategoryBinding
    private lateinit var allAlcoholMainCategoryAdapter: AllAlcoholMainCategoryAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var subCategoryList: ArrayList<String>
    private var type: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholmaincategoryBinding.inflate(layoutInflater)
        arguments?.let {
            type = it.getInt("type")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서브카테고리 List를 DB에서 불러온다
        // List를 이용해 서브카테고리 탭을 생성한다
        // 서브카테고리 탭을 변경할 때 마다 뷰페이저2를 이용해 서브카테고리 프래그먼트를 변경해준다
        loadSubCategoryList(type!!)
        allAlcoholMainCategoryAdapter = AllAlcoholMainCategoryAdapter(this)
        viewPager2 = binding.viewpager2AllalcoholmaincategoryFragmentcontainer
        viewPager2.adapter = allAlcoholMainCategoryAdapter
        setTabTitle()
    }

    private fun loadSubCategoryList(category: Int) {
        when (category) {
            Const.LIQUOR -> subCategoryList = arrayListOf("보드카", "럼", "위스키", "브랜디", "리큐르")
            Const.BEER -> subCategoryList = arrayListOf("에일", "라거", "무알콜")
            Const.TRADITIONAL -> subCategoryList = arrayListOf("과실주","탁주","청주","증류주","약주","살균약주")
            Const.WINE -> subCategoryList = arrayListOf("레드","화이트","로제","스파클링","샴페인")
        }
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
                putInt("type", type!!)
                putString("category", subCategoryList[position]) //DB 연결 후 서브클래스 정보로 변경 필요
            }
            return fragment
        }

    }
}