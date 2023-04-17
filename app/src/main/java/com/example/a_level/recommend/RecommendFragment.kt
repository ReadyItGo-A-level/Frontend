package com.example.a_level.recommend

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.App
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendBinding
import com.example.a_level.keyword.UserKeywordActivity
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
    private lateinit var tabTextList: ArrayList<String>

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

        binding.imageviewEditRecommend.setOnClickListener {
            val intent= Intent(requireContext(),UserKeywordActivity::class.java)
            startActivity(intent)
        }

        binding.textviewRecommendUsername.text= App.prefs.getString("username","유저")
    }

    override fun onResume() {
        super.onResume()
        recommendResponse()
    }

    private fun recommendResponse() {
        RecommendService.getRetrofitRecommend().enqueue(object : Callback<RecommendResponse> {
            override fun onResponse(
                call: Call<RecommendResponse>,
                response: Response<RecommendResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())

                    var alcohol = response.body()?.data?.alcohol
                    var post = response.body()?.data?.post
                    var topPost = response.body()?.data?.topPost

                    Log.e("post","${post}")
                    Log.e("topPost","${topPost}")

                    tabTextList = arrayListOf()
                    tabTextList.add("전체")

                    var allData = arrayListOf<RecommendUserRecyclerViewData>()
                    var traditionData = arrayListOf<RecommendUserRecyclerViewData>()
                    var liquorData = arrayListOf<RecommendUserRecyclerViewData>()
                    var wineData = arrayListOf<RecommendUserRecyclerViewData>()
                    var beerData = arrayListOf<RecommendUserRecyclerViewData>()

                    var url="http://13.125.232.247:8080"

                    if (alcohol != null) {
                        if (alcohol.wine.size != 0) {
                            tabTextList.add("와인")
                        }
                        if (alcohol.beer.size != 0) {
                            tabTextList.add("맥주")
                        }
                        if (alcohol.sool.size != 0) {
                            tabTextList.add("전통주")
                        }
                        if (alcohol.liquor.size != 0) {
                            tabTextList.add("양주")
                        }

                        for(i in 0 until alcohol.alcohols.size) {
                            if (alcohol.alcohols.size != 0) {
                                var image= if (alcohol.alcohols[i].image==null) "" else url + alcohol.alcohols[i].image
                                allData.add(RecommendUserRecyclerViewData(image,alcohol.alcohols[i].name))
                            }
                            if (alcohol.wine.size != 0) {
                                var image= if (alcohol.wine[i].image==null) "" else url + alcohol.wine[i].image
                                wineData.add(RecommendUserRecyclerViewData(image, alcohol.wine[i].name))
                            }
                            if (alcohol.beer.size != 0) {
                                var image= if (alcohol.beer[i].image==null) "" else url + alcohol.beer[i].image
                                beerData.add(RecommendUserRecyclerViewData(image, alcohol.beer[i].name))
                            }
                            if (alcohol.sool.size != 0) {
                                var image= if (alcohol.sool[i].image==null) "" else url + alcohol.sool[i].image
                                traditionData.add(RecommendUserRecyclerViewData(image, alcohol.sool[i].name))
                            }
                            if (alcohol.liquor.size != 0) {
                                var image= if (alcohol.sool[i].image==null) "" else url + alcohol.sool[i].image
                                liquorData.add(RecommendUserRecyclerViewData(image, alcohol.liquor[i].name))
                            }
                        }
                    }

                    var alcoholList =
                        RecommendAlcoholData(allData, traditionData, liquorData, wineData, beerData)
                    binding.viewpagerRecommendUser.adapter =
                        RecommendViewPagerAdapter(this@RecommendFragment, alcoholList, tabTextList)
                    binding.viewpagerRecommendUser.isUserInputEnabled = false
                    TabLayoutMediator(
                        binding.tablayoutRecommendAlcohol,
                        binding.viewpagerRecommendUser
                    ) { tab, pos ->
                        tab.text = tabTextList[pos]
                    }.attach()
                    setTabItemMargin(binding.tablayoutRecommendAlcohol, 20)

                    recommendSimilarRecyclerViewData= arrayListOf()
                    if (post != null) {
                        for (i in 0 until post.size) {
                            var id = post[i].id
                            var title = post[i].title
                            var content = post[i].content
                            var image = if(post[i].image==null) "" else url+post[i].image
                            var commentCount = post[i].commentCount
                            var scrapCount = post[i].scrapCount
                            var likeCount = post[i].likeCount
                            recommendSimilarRecyclerViewData.add(
                                RecommendSimilarRecyclerViewData(
                                    id,
                                    title,
                                    content,
                                    image,
                                    commentCount,
                                    scrapCount,
                                    likeCount
                                )
                            )
                        }
                    }

                    if(recommendSimilarRecyclerViewData.size!=0) {
                        binding.recyclerviewSimilarRecommend.apply {
                            layoutManager =
                                GridLayoutManager(
                                    requireContext(),
                                    recommendSimilarRecyclerViewData.size,
                                    GridLayoutManager.VERTICAL,
                                    false
                                )
                            adapter = RecommendSimilarRecyclerViewAdapter(
                                requireContext(),
                                recommendSimilarRecyclerViewData
                            )
                        }
                    }else{
                        binding.textviewRecommendUserReady.visibility=View.VISIBLE
                        binding.recyclerviewSimilarRecommend.visibility=View.GONE
                    }

                    recommendPopularRecyclerViewData= arrayListOf()
                    if (topPost != null) {
                        for (i in 0 until topPost.size) {
                            var id = topPost[i].id
                            var title = topPost[i].title
                            var content = topPost[i].content
                            var image = if(topPost[i].image==null) "" else url+topPost[i].image
                            var commentCount = topPost[i].commentCount
                            var scrapCount = topPost[i].scrapCount
                            var likeCount = topPost[i].likeCount

                            recommendPopularRecyclerViewData.add(
                                RecommendPopularRecyclerViewData(
                                    id,
                                    title,
                                    content,
                                    image,
                                    commentCount,
                                    scrapCount,
                                    likeCount
                                )
                            )
                        }
                    }

                    if(recommendPopularRecyclerViewData.size!=0) {
                        binding.recyclerviewPopularRecommend.apply {
                            layoutManager =
                                GridLayoutManager(
                                    requireContext(),
                                    recommendPopularRecyclerViewData.size,
                                    GridLayoutManager.VERTICAL,
                                    false
                                )
                            adapter = RecommendPopularRecyclerViewAdapter(
                                requireContext(),
                                recommendPopularRecyclerViewData
                            )
                        }
                    }
                    else{
                        binding.textviewRecommendPopularReady.visibility=View.VISIBLE
                        binding.recyclerviewPopularRecommend.visibility=View.GONE
                    }
                } else {
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

    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int = 20) {
        for (i in tabTextList.indices) {
            val tabs = tabLayout.getChildAt(0) as ViewGroup
            for (i in 0 until tabs.childCount) {
                val tab = tabs.getChildAt(i)
                val lp = tab.layoutParams as LinearLayout.LayoutParams
                lp.marginEnd = marginEnd
                tab.layoutParams = lp
                tabLayout.requestLayout()
            }
        }
    }
}