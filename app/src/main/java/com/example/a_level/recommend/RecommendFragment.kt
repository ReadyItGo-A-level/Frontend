package com.example.a_level.recommend

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.App
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendBinding
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding
    private lateinit var recommendUserAdapter: RecommendUserRecyclerViewAdapter
    private lateinit var recommendUserRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendSimilarRecyclerViewData: ArrayList<RecommendSimilarRecyclerViewData>
    private lateinit var recommendPopularRecyclerViewData: ArrayList<RecommendPopularRecyclerViewData>
    private lateinit var recommendList: RecommendResponse

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
        recommendResponse() //데이터 호출
        initRecommendRecyclerView()


        tapSelected()

//        동적으로 탭 만들기

    }
    private fun recommendResponse(){
        RecommendService.getRetrofitRecommend(App.prefs.userid!!).enqueue(object: Callback<RecommendResponse>{
            override fun onResponse(
                call: Call<RecommendResponse>,
                response: Response<RecommendResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())

                    val tablayout=binding.tablayoutRecommendAlcohol
                    val tab1: TabLayout.Tab=tablayout.newTab()

//                    recommendList=response.body()!!
//                    for (i in 1..recommendList.data.alcohol.alcohols.size){  //전체
//                        var image=recommendList.data.alcohol.alcohols[i].image
//                        var name=recommendList.data.alcohol.alcohols[i].name
//                        var str=name.split(",")
//                        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData(image,str[0], str[1]))
//                    }
//
//                    if(recommendList.data.alcohol.wine.size!=0){
//
//                    }
//                    if(recommendList.data.alcohol.beer.size!=0){
//
//                    }
//                    if(recommendList.data.alcohol.sool.size!=0){
//
//                    }
//                    if(recommendList.data.alcohol.liquor.size!=0){
//
//                    }
//
//                    for(i in 1..recommendList.data.post.size){
//                        var id=recommendList.data.post[i].id
//                        var title=recommendList.data.post[i].title
//                        var content=recommendList.data.post[i].content
//                        var image=recommendList.data.post[i].image
//                        var commentCount=recommendList.data.post[i].commentCount
//                        var scrapCount=recommendList.data.post[i].scrapCount
//                        var likeCount=recommendList.data.post[i].likeCount
//
//                        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(id, title, content, image, commentCount, scrapCount, likeCount))
//                    }
//
//                    for(i in 1..recommendList.data.topPost.size){
//                        var id=recommendList.data.topPost[i].id
//                        var title=recommendList.data.topPost[i].title
//                        var content=recommendList.data.topPost[i].content
//                        var image=recommendList.data.topPost[i].image
//                        var commentCount=recommendList.data.topPost[i].commentCount
//                        var scrapCount=recommendList.data.topPost[i].scrapCount
//                        var likeCount=recommendList.data.topPost[i].likeCount
//
//                        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(id, title, content, image, commentCount, scrapCount, likeCount))
//                    }

                }else{
                    try {
                        val body = response.errorBody()!!.string()

                        Log.e(ContentValues.TAG, "body : $body")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                Log.d("TAG", "실패원인: {$t}")
            }
        })
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
//        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("오이스터 베이", "쇼비농 블랑"))
//        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("보야", "피노누아"))
//        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("엠", "로제"))
//        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("칸티", "모스카토 다스티"))
//        recommendUserRecyclerViewData.add(RecommendUserRecyclerViewData("간치아", "모스카토 로제"))

        recommendSimilarRecyclerViewData= arrayListOf()
//        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 맛있는 맥주", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대 호프집!","1", "3", "3"))
//        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
//        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
//        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))
//        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData("감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","1", "3", "3"))

        recommendPopularRecyclerViewData= arrayListOf()
//        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("와인에 빠져들어보자구", "와인 어렵지?","100", "33", "15"))
//        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("오늘 세일하는 와인", "오늘 세일하는 와인 url 모음","93", "99", "87"))
//        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("와인 코르크 쉽게 따기", "코르크 따다가 와인에 빠뜨린 게","89", "16", "17"))
//        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("더 맛있게 마시기", "술을 더 건강하게 마시기 위해서는!","51", "9", "11"))
//        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData("글 쓰기 힘들다", "이게 왜 인기에 ...?","31", "3", "34"))
    }

    private fun replaceFragment(fragment: Fragment, category: Int) {
        val bundle = Bundle()
        bundle.putInt("category", category)
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .replace(R.id.framelayout_allalcohol_fragmentcontainer, fragment).commit()
    }
}