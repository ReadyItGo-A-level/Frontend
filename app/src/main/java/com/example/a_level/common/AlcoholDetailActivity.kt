package com.example.a_level.common

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.databinding.ActivityAlcoholdetailBinding

class AlcoholDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlcoholdetailBinding
    private lateinit var alcoholDetailReviewRecyclerViewAdapter: AlcoholDetailReviewRecyclerViewAdapter
    private lateinit var alcoholDetailPostReviewRecyclerViewAdapter: AlcoholDetailPostRecyclerViewAdapter
    private lateinit var alcoholDetailRecommendRecyclerViewAdapter: AlcoholDetailPostRecyclerViewAdapter
    private lateinit var alcoholDetailReviewRecyclerViewData: ArrayList<AlcoholDetailReviewRecyclerViewData>
    private lateinit var alcoholDetailPostReviewRecyclerViewData: ArrayList<AlcoholDetailPostRecyclerViewData>
    private lateinit var alcoholDetailRecommendRecyclerViewData: ArrayList<AlcoholDetailPostRecyclerViewData>

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
        //기타 UI 설정하기
        initUI()
    }

    private fun loadData() {
        alcoholDetailReviewRecyclerViewData = arrayListOf()
        alcoholDetailReviewRecyclerViewData.add(
            AlcoholDetailReviewRecyclerViewData(
                "테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 ",
                "user1",
                "0000.00.00"
            )
        )
        alcoholDetailReviewRecyclerViewData.add(
            AlcoholDetailReviewRecyclerViewData(
                "테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 ",
                "user2",
                "0000.00.00"
            )
        )
        alcoholDetailReviewRecyclerViewData.add(
            AlcoholDetailReviewRecyclerViewData(
                "테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 100자입니다 테스트 ",
                "user3",
                "0000.00.00"
            )
        )

        alcoholDetailPostReviewRecyclerViewData = arrayListOf()
        alcoholDetailPostReviewRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다1", "테스트 입니다.", 1, 2, 3)
        )
        alcoholDetailPostReviewRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다2", "테스트 입니다.", 1, 2, 3)
        )
        alcoholDetailPostReviewRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다3", "테스트 입니다.", 1, 2, 3)
        )
        alcoholDetailPostReviewRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다4", "테스트 입니다.", 1, 2, 3)
        )

        alcoholDetailRecommendRecyclerViewData = arrayListOf()
        alcoholDetailRecommendRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다1", "테스트 입니다.", 1, 2, 3)
        )
        alcoholDetailRecommendRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다2", "테스트 입니다.", 1, 2, 3)
        )
        alcoholDetailRecommendRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다3", "테스트 입니다.", 1, 2, 3)
        )
        alcoholDetailRecommendRecyclerViewData.add(
            AlcoholDetailPostRecyclerViewData("테스트입니다4", "테스트 입니다.", 1, 2, 3)
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
        alcoholDetailReviewRecyclerViewAdapter =
            AlcoholDetailReviewRecyclerViewAdapter(alcoholDetailReviewRecyclerViewData).apply {
                setOnItemLongClickListener(object :
                    AlcoholDetailReviewRecyclerViewAdapter.OnItemLongClickListener {
                    override fun onItemLongClick(
                        v: View,
                        item: AlcoholDetailReviewRecyclerViewData
                    ): Boolean? {
                        val builder = AlertDialog.Builder(this@AlcoholDetailActivity)
                        builder.setMessage("리뷰를 삭제하시겠습니까?")
                            .setNegativeButton("취소", DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(applicationContext, "취소했습니다.", Toast.LENGTH_SHORT)
                                    .show()
                            })
                            .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(applicationContext, "확인했습니다.", Toast.LENGTH_SHORT)
                                    .show()
                            })

                        //데이터 삭제 요청 후 데이터 다시 불러오기
                        //데이터 변경 알리기
                        builder.show()
                        return true
                    }
                })
            }
        binding.recyclerviewAlcoholdetailReview.apply {
            layoutManager =
                LinearLayoutManager(context)
            this.setHasFixedSize(false)
            adapter = alcoholDetailReviewRecyclerViewAdapter
        }
        if (alcoholDetailReviewRecyclerViewData != null) {
            binding.textviewAlcoholdetailShortreviewplaceholder.visibility = View.GONE
            binding.recyclerviewAlcoholdetailReview.visibility = View.VISIBLE
        }

        if (alcoholDetailPostReviewRecyclerViewData != null) {
            binding.textviewAlcoholdetailPostreviewplaceholder.visibility = View.GONE
            binding.recyclerviewAlcoholdetailPostreview.visibility = View.VISIBLE
            alcoholDetailPostReviewRecyclerViewAdapter =
                AlcoholDetailPostRecyclerViewAdapter(alcoholDetailPostReviewRecyclerViewData)
            binding.recyclerviewAlcoholdetailPostreview.adapter =
                alcoholDetailPostReviewRecyclerViewAdapter

        }

        if (alcoholDetailRecommendRecyclerViewData != null) {
            binding.recyclerviewAlcoholdetailRecommend.visibility = View.VISIBLE
            alcoholDetailRecommendRecyclerViewAdapter =
                AlcoholDetailPostRecyclerViewAdapter(alcoholDetailRecommendRecyclerViewData)
            binding.recyclerviewAlcoholdetailRecommend.adapter =
                alcoholDetailRecommendRecyclerViewAdapter

        }
    }

    private fun initUI() {
        binding.textviewAlcoholdetailWritepostreview.setOnClickListener {

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