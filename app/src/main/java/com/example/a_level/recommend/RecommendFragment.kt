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
import com.google.android.material.tabs.TabLayout

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding
    private lateinit var recommendUserAdapter: RecommendUserRecyclerViewAdapter
    private lateinit var recommendUserRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendSimilarRecyclerViewData: ArrayList<RecommendSimilarRecyclerViewData>

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
        tapSelected()
    }

    private fun tapSelected(){
        binding.tablayoutRecommendAlcohol.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {


                }
            }
        })
    }

    private fun initRecommendRecyclerView(){
        binding.recyclerviewUserRecommend.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendUserRecyclerViewAdapter(
                requireContext(),
                recommendUserRecyclerViewData
            )
        }

        binding.recyclerviewSimilarRecommend.apply{
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendSimilarRecyclerViewAdapter(
                requireContext(),
                recommendSimilarRecyclerViewData
            )
        }
//        val adapter=RecommendUserRecyclerViewAdapter() //어댑터 객체 만듦
//        adapter.datalist=mDatas //데이터 넣어줌
//        binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
//        binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    private fun initializelist(){ //임의로 데이터 넣어서 만들어봄
        recommendUserRecyclerViewData= arrayListOf()
        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("오이스터 베이", "쇼비농 블랑"))
        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("보야", "피노누아"))
        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("엠", "로제"))
        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("칸티", "모스카토 다스티"))
        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("간치아", "모스카토 로제"))

        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
    }

    private fun replaceFragment(fragment: Fragment, category: Int) {
        val bundle = Bundle()
        bundle.putInt("category", category)
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .replace(R.id.framelayout_allalcohol_fragmentcontainer, fragment).commit()
    }
}