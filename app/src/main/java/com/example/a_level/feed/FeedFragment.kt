package com.example.a_level.feed

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.common.AlcoholDetailReviewRecyclerViewAdapter
import com.example.a_level.common.AlcoholDetailReviewRecyclerViewData
import com.example.a_level.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private lateinit var feedRecyclerViewData: ArrayList<FeedRecyclerViewData>
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
        feedRecyclerViewData = arrayListOf()
        feedRecyclerViewData.add(FeedRecyclerViewData("", "", 0, 0, 0, 0))
        feedRecyclerViewData.add(
            FeedRecyclerViewData("게시글 제목1", "게시글 내용 입니다.", 1, 2, 3, 1)
        )
        feedRecyclerViewData.add(
            FeedRecyclerViewData("게시글 제목2", "게시글 내용 입니다.", 1, 2, 3, 1)
        )
        feedRecyclerViewData.add(
            FeedRecyclerViewData("게시글 제목3", "게시글 내용 입니다.", 1, 2, 3, 1)
        )
    }

    private fun initToolBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFeed)
        val toolBar = (activity as AppCompatActivity).supportActionBar
        toolBar?.title = "피드"
        toolBar?.setDisplayHomeAsUpEnabled(false)

    }

    private fun initRecyclerView() {
        feedRecyclerViewAdapter = FeedRecyclerViewAdapter(feedRecyclerViewData).apply {
            setOnItemClickListener(object : FeedRecyclerViewAdapter.OnItemClickListener{
                override fun onItemClick(v: View, item: FeedRecyclerViewData){
                    startActivity(Intent(requireContext(), FeedDetailActivity::class.java))
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