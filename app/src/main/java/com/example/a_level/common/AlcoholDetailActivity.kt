package com.example.a_level.common

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.databinding.ActivityAlcoholdetailBinding

class AlcoholDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlcoholdetailBinding
    private lateinit var alcoholDetailRecyclerViewAdapter: AlcoholDetailRecyclerViewAdapter
    private lateinit var alcoholDetailRecyclerViewData: ArrayList<AlcoholDetailRecyclerViewData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlcoholdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //데이터 불러오기
        loadData()
        //툴바 적용하기
        initActionBar()
        //뷰에 데이터 적용하기
        setDataOnView()
        //리사이클러뷰 설정하기
        initRecyclerView()
    }

    private fun loadData() {
        alcoholDetailRecyclerViewData = arrayListOf()
        alcoholDetailRecyclerViewData.add(
            AlcoholDetailRecyclerViewData(
                "테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자",
                "user1",
                "0000.00.00"
            )
        )
        alcoholDetailRecyclerViewData.add(
            AlcoholDetailRecyclerViewData(
                "테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자",
                "user2",
                "0000.00.00"
            )
        )
        alcoholDetailRecyclerViewData.add(
            AlcoholDetailRecyclerViewData(
                "테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자",
                "user3",
                "0000.00.00"
            )
        )
        alcoholDetailRecyclerViewData.add(
            AlcoholDetailRecyclerViewData(
                "테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자",
                "user4",
                "0000.00.00"
            )
        )
        alcoholDetailRecyclerViewData.add(
            AlcoholDetailRecyclerViewData(
                "테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자",
                "user5",
                "0000.00.00"
            )
        )
        alcoholDetailRecyclerViewData.add(
            AlcoholDetailRecyclerViewData(
                "테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다테스트300자입니다테스트300자입니다테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자입니다 테스트300자",
                "user6",
                "0000.00.00"
            )
        )

    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarAlcoholdetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun setDataOnView() {

    }

    private fun initRecyclerView() {

        alcoholDetailRecyclerViewAdapter =
            AlcoholDetailRecyclerViewAdapter(this, alcoholDetailRecyclerViewData)
        binding.recyclerviewAlcoholdetailReview.apply {
            layoutManager =
                LinearLayoutManager(context)
            adapter = alcoholDetailRecyclerViewAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}