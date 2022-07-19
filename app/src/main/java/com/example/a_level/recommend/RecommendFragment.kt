package com.example.a_level.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.allalcohol.AllAlcoholSubCategoryRecyclerViewAdapter
import com.example.a_level.allalcohol.AllAlcoholSubCategoryRecyclerViewData
import com.example.a_level.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding
    private lateinit var adapter: RecommendUserRecyclerViewAdapter
    private lateinit var RecommendUserRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializelist()
        initRecommendRecyclerView()

        val tabHost : TabHost = findViewById<TabHost>(R.id.tabs)

        val tab1 = tabHost.newTabSpec("tab1").setIndicator("전체")
        tab1.setContent(R.id.tab1)
        tabHost.addTab(tab1)


    }

    private fun initRecommendRecyclerView(){
        binding.recyclerviewUserRecommend.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendUserRecyclerViewAdapter(
                requireContext(),
                RecommendUserRecyclerViewData
            )
        }

//        val adapter=RecommendUserRecyclerViewAdapter() //어댑터 객체 만듦
//        adapter.datalist=mDatas //데이터 넣어줌
//        binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
//        binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    private fun initializelist(){ //임의로 데이터 넣어서 만들어봄
        RecommendUserRecyclerViewData= arrayListOf()
        RecommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("오이스터 베이", "쇼비농 블랑"))
        RecommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("보야", "피노누아"))
        RecommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("엠", "로제"))
        RecommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("칸티", "모스카토 다스티"))
        RecommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("간치아", "모스카토 로제"))

    }
}