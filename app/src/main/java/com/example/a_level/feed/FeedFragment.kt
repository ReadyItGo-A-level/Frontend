package com.example.a_level.feed

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
import com.example.a_level.feed.model.response.Post

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
    private lateinit var posts: ArrayList<Post?>
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
        loadData()
        initToolBar()
        initRecyclerView()
        initUi()
    }

    private fun loadData() {
        val tempPosts = ArrayList<Post>()
        tempPosts.add(
            Post(
                null,
                null,
                "게시글 제목",
                "게시글 내용",
                null,
                null,
                "까르띠에 소비뇽 500년산",
                "와인",
                null,
                "2(낮음)",
                2000000,
                null,
                null,
                null,
                null,
                null,
                3,
                2,
                12
            )
        )
        tempPosts.add(
            Post(
                null,
                null,
                "게시글 제목",
                "게시글 내용",
                null,
                null,
                "까르띠에 소비뇽 500년산",
                "와인",
                null,
                "2(낮음)",
                2000000,
                null,
                null,
                null,
                null,
                null,
                3,
                2,
                12
            )
        )
        posts = arrayListOf()
        posts.add(null)
        posts.addAll(tempPosts)
        Log.d("siiipal", posts[0].toString())
    }

    private fun initToolBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFeed)
        val toolBar = (activity as AppCompatActivity).supportActionBar
        toolBar?.title = "피드"
        toolBar?.setDisplayHomeAsUpEnabled(false)

    }

    private fun initRecyclerView() {
        feedRecyclerViewAdapter = FeedRecyclerViewAdapter(posts).apply {
            setOnItemClickListener(object : FeedRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(v: View, item: Post?) {
                    val intent = Intent(requireContext(), FeedDetailActivity::class.java)
                    intent.putExtra("id", item?.id ?: 0)
                    startActivity(intent)
                }
            })
        }
        binding.recyclerviewFeed.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = feedRecyclerViewAdapter
        }
    }

    private fun initUi() {
        binding.floatingbuttonFeed.setOnClickListener {
            startActivity(Intent(requireContext(), WriteFeedActivity::class.java))
        }
    }
}