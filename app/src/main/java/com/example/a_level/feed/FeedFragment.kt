package com.example.a_level.feed

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.databinding.FragmentFeedBinding
import com.example.a_level.feed.model.response.FeedList
import com.example.a_level.feed.model.response.FeedListResponse
import com.example.a_level.feed.model.response.FeedService
import com.example.a_level.feed.model.response.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding

    /* 주의사항
    * FeedRecyclerViewAdapter에는 두가지 View가 있습니다.
    * 하나는 0번째 인덱스에 들어가는 "~~~다양한 술얘기를 나눠보세요" 뷰 (viewType == Description)
    * 다른 하나는 1번째 인덱스 이후에 들어가는 post 뷰 (viewType == Data)
    * loadData()에 지금 구성한 것처럼 posts.add(null) 하여 0번째 인덱스에 null이 들어갈 수 있도록 해주세요
    * adpater에서 0번째 인덱스가 null인지 검사해 Description인지 판별합니다.
    * 이렇게 한 이유 = Description 뷰도 아래로 스크롤하면 사라지게 해야해서
    * 더 좋은 방법 있으면 얼마든지 change 하시길..
    * */
    private lateinit var posts: ArrayList<FeedList>
    private lateinit var feedRecyclerViewAdapter: FeedRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        initUi()
    }

    override fun onResume() {
        super.onResume()
        loadData()

    }

    private fun loadData() {
        posts = arrayListOf()

        FeedService.retrofitGetFeedList().enqueue(object: Callback<FeedListResponse>{
            override fun onResponse(
                call: Call<FeedListResponse>,
                response: Response<FeedListResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("log", response.toString())
                    Log.e("log", response.body().toString())

                    var data=response.body()?.data
                    var url="http://13.125.232.247:8080"

                    if(data!=null) {
                        for (i in 0 until data.size){
                            var id=data[i].id
                            var username=data[i].username
                            var title=data[i].title
                            var content=data[i].content
                            var image=if(data[i].image==null) "" else url+data[i].image
                            var hit=data[i].hit
                            var commentCount=data[i].commentCount
                            var scrapCount=data[i].scrapCount
                            var likeCount=data[i].likeCount
                            var alcoholName=data[i].alcoholName
                            var alcoholType=data[i].alcoholType
                            var flavor=data[i].flavor
                            var volume=if(data[i].volume==null) 0 else data[i].volume
                            var price=data[i].price
                            var body=data[i].body
                            var sugar=data[i].sugar
                            var modifiedDate=data[i].modifiedDate

                            posts.add(FeedList(id,username,title, content, image,hit,commentCount,scrapCount,likeCount,alcoholName,alcoholType,flavor,volume,price, body,sugar,modifiedDate))
                        }
                    }

                    initRecyclerView()
                } else {
                    try {
                        val body = response.errorBody()!!.string()

                        Log.e(ContentValues.TAG, "body : $body")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<FeedListResponse>, t: Throwable) {
                Log.e("TAG", "실패원인: {$t}")
            }
        })
    }

    private fun initToolBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFeed)
        val toolBar = (activity as AppCompatActivity).supportActionBar
        toolBar?.title = "피드"
        toolBar?.setDisplayHomeAsUpEnabled(false)

    }

    private fun initRecyclerView() {
//        feedRecyclerViewAdapter = FeedRecyclerViewAdapter(posts).apply {
//            setOnItemClickListener(object : FeedRecyclerViewAdapter.OnItemClickListener {
//                override fun onItemClick(v: View, item: Post?) {
//                    val intent = Intent(requireContext(), FeedDetailActivity::class.java)
//                    intent.putExtra("id", item?.id ?: 0)
//                    startActivity(intent)
//                }
//            })
//        }
        binding.recyclerviewFeed.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FeedRecyclerViewAdapter(requireContext(),posts)
        }
    }

    private fun initUi() {
        binding.floatingbuttonFeed.setOnClickListener {
            startActivity(Intent(requireContext(), WriteFeedActivity::class.java))
        }
    }
}