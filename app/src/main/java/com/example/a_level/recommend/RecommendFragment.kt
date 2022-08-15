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
    private lateinit var recommendPopularRecyclerViewData: ArrayList<RecommendPopularRecyclerViewData>

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

//        동적으로 탭 만들기
//        val tabLayout = binding.tabs
//        val tab1: TabLayout.Tab=TabLayout.newTab()
//        tab1.text="술"
//        tabLayout.addTab(tab1)
    }

    private fun tapSelected(){
        binding.tablayoutRecommendAlcohol.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0->{

                    }
                    1->{

                    }
                    2->{

                    }
                    3->{

                    }
                    4->{

                    }
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

        binding.recyclerviewPopularRecommend.apply{
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendPopularRecyclerViewAdapter(
                requireContext(),
                recommendPopularRecyclerViewData
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

        recommendSimilarRecyclerViewData= arrayListOf()
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 맛있는 맥주", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대 호프집!","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))

        recommendPopularRecyclerViewData= arrayListOf()
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("와인에 빠져들어보자구", "와인 어렵지?","100", "33", "15"))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("오늘 세일하는 와인", "오늘 세일하는 와인 url 모음","93", "99", "87"))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("와인 코르크 쉽게 따기", "코르크 따다가 와인에 빠뜨린 게","89", "16", "17"))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("더 맛있게 마시기", "술을 더 건강하게 마시기 위해서는!","51", "9", "11"))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("글 쓰기 힘들다", "이게 왜 인기에 ...?","31", "3", "34"))
    }

    private fun replaceFragment(fragment: Fragment, category: Int) {
        val bundle = Bundle()
        bundle.putInt("category", category)
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .replace(R.id.framelayout_allalcohol_fragmentcontainer, fragment).commit()
    }
}