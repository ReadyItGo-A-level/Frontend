package com.example.a_level.recommend

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.App
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding
    private lateinit var recommendUserAdapter: RecommendUserRecyclerViewAdapter
    private lateinit var recommendUserRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendAllRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendWineRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendBeerRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendSoolRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendLiquorRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    private lateinit var recommendSimilarRecyclerViewData: ArrayList<RecommendSimilarRecyclerViewData>
    private lateinit var recommendPopularRecyclerViewData: ArrayList<RecommendPopularRecyclerViewData>
    private lateinit var recommendList: RecommendResponse
    private var tabTextList= arrayListOf("전체", "맥주","와인","양주","전통주") //"전체", "맥주","와인","양주","전통주"

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
//        recommendResponse() //데이터 호출

        var allData= arrayListOf(
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"))
        var wineData=arrayListOf(
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"))
        var beerData=arrayListOf(
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"))
        var liquorData=arrayListOf(
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"))
        var traditionData=arrayListOf(
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"),
            RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"))

        var alcoholList=RecommendAlcoholData(allData,traditionData,liquorData,wineData,beerData)
        binding.viewpagerRecommendUser.adapter=RecommendViewPagerAdapter(this,alcoholList, tabTextList)
        binding.viewpagerRecommendUser.isUserInputEnabled=false
        TabLayoutMediator(binding.tablayoutRecommendAlcohol, binding.viewpagerRecommendUser){tab, pos->
            tab.text = tabTextList[pos]
        }.attach()
        setTabItemMargin(binding.tablayoutRecommendAlcohol,20)
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

                    var alcohol=response.body()?.data?.alcohol
                    var post=response.body()?.data?.post
                    var topPost=response.body()?.data?.topPost

                    tabTextList = arrayListOf("전체")

                    if(alcohol !=null){
                        if(alcohol.wine.size!=0){
                            tabTextList.add("와인")
                        }
                        else if(alcohol.beer.size!=0){
                            tabTextList.add("맥주")
                        }
                        else if(alcohol.sool.size!=0){
                            tabTextList.add("전통주")
                        }
                        else if(alcohol.liquor.size!=0){
                            tabTextList.add("양주")
                        }
                    }


//                    val tablayout=binding.tablayoutRecommendAlcohol
//                    val tab1: TabLayout.Tab=tablayout.newTab()

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
                    if (post != null) {
                        for(i in 1..post.size){
                            var id=recommendList.data.post[i].id
                            var title=recommendList.data.post[i].title
                            var content=recommendList.data.post[i].content
                            var image=recommendList.data.post[i].image
                            var commentCount=recommendList.data.post[i].commentCount
                            var scrapCount=recommendList.data.post[i].scrapCount
                            var likeCount=recommendList.data.post[i].likeCount

                            recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(id, title, content, image, commentCount, scrapCount, likeCount))
                        }
                    }
//
                    if (topPost != null) {
                        for(i in 1..topPost.size){
                            var id=recommendList.data.topPost[i].id
                            var title=recommendList.data.topPost[i].title
                            var content=recommendList.data.topPost[i].content
                            var image=recommendList.data.topPost[i].image
                            var commentCount=recommendList.data.topPost[i].commentCount
                            var scrapCount=recommendList.data.topPost[i].scrapCount
                            var likeCount=recommendList.data.topPost[i].likeCount

                            recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(id, title, content, image, commentCount, scrapCount, likeCount))
                        }
                    }

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
//        binding.recyclerviewUserRecommend.apply {
//            layoutManager =
//                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
//            adapter = RecommendUserRecyclerViewAdapter(
//                requireContext(),
//                recommendUserRecyclerViewData
//            )
//        }

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
//        val adapter=RecommendUserRecyclerViewAdapter(requireContext(),) //어댑터 객체 만듦
//        adapter.datalist=mDatas //데이터 넣어줌
//        binding.recyclerviewUserRecommend.adapter=adapter //리사이클러뷰에 어댑터 연결
//        binding.recyclerviewUserRecommend.layoutManager= LinearLayoutManager(requireContext()) //레이아웃 매니저 연결
    }

    private fun initializelist(){ //임의로 데이터 넣어서 만들어봄
//        recommendAllRecyclerViewData= arrayListOf()
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("","오이스터 베이", "쇼비농 블랑"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("","보야", "피노누아"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("","엠", "로제"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("","칸티", "모스카토 다스티"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("","간치아", "모스카토 로제"))
//
//        recommendWineRecyclerViewData= arrayListOf()
//        recommendWineRecyclerViewData.add(RecommendUserRecyclerViewData("","오이스터 베이", "쇼비농 블랑"))
//        recommendWineRecyclerViewData.add(RecommendUserRecyclerViewData("","보야", "피노누아"))
//        recommendWineRecyclerViewData.add(RecommendUserRecyclerViewData("","엠", "로제"))
//        recommendWineRecyclerViewData.add(RecommendUserRecyclerViewData("","칸티", "모스카토 다스티"))
//        recommendWineRecyclerViewData.add(RecommendUserRecyclerViewData("","간치아", "모스카토 로제"))

        recommendSimilarRecyclerViewData= arrayListOf()
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(1,"감튀랑 찰떡 궁합인 맛있는 맥주", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대 호프집!","", 3, 3, 1))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(2,"감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","", 3, 3, 1))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(3,"감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","", 3, 3, 1))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(4,"감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","", 3, 3, 1))
        recommendSimilarRecyclerViewData.add(RecommendSimilarRecyclerViewData(5,"감튀랑 찰떡 궁합인 신본 걸", "감튀랑 찰떡 궁합 신상 맥주 소개드립니다! 서초구 최대","", 3, 3, 1))

        recommendPopularRecyclerViewData= arrayListOf()
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(6,"와인에 빠져들어보자구", "와인 어렵지?","",100, 33, 15))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(7,"오늘 세일하는 와인", "오늘 세일하는 와인 url 모음","",93, 99, 87))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(8,"와인 코르크 쉽게 따기", "코르크 따다가 와인에 빠뜨린 게","",89, 16, 17))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(9,"더 맛있게 마시기", "술을 더 건강하게 마시기 위해서는!","",51, 9, 11))
        recommendPopularRecyclerViewData.add(RecommendPopularRecyclerViewData(10,"글 쓰기 힘들다", "이게 왜 인기에 ...?","",31, 3, 34))
    }

    private fun replaceFragment(fragment: Fragment, category: Int) {
        val bundle = Bundle()
        bundle.putInt("category", category)
        fragment.arguments = bundle
        childFragmentManager.beginTransaction()
            .replace(R.id.framelayout_allalcohol_fragmentcontainer, fragment).commit()
    }

    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int = 20) {
        for(i in tabTextList.indices) {
            val tabs = tabLayout.getChildAt(0) as ViewGroup
            for(i in 0 until tabs.childCount) {
                val tab = tabs.getChildAt(i)
                val lp = tab.layoutParams as LinearLayout.LayoutParams
                lp.marginEnd = marginEnd
                tab.layoutParams = lp
                tabLayout.requestLayout()
            }
        }
    }
}