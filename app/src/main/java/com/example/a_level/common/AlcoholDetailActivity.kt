package com.example.a_level.common

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a_level.R
import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.common.AlcoholDetailPostRecyclerViewAdapter.OnItemClickListener
import com.example.a_level.common.model.response.AlcoholDetailResponse
import com.example.a_level.common.model.response.DefaultResponse
import com.example.a_level.common.model.response.Review
import com.example.a_level.common.service.CommonService
import com.example.a_level.databinding.ActivityAlcoholdetailBinding
import com.example.a_level.feed.model.response.Post
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlcoholDetailActivity : AppCompatActivity() {

    private var alcoholId: Long = 0
    private lateinit var binding: ActivityAlcoholdetailBinding
    private lateinit var reviews: List<Review>
    private lateinit var recommendationPosts: List<Post>
    private lateinit var posts: List<Post>
    private lateinit var alcoholInformation: ArrayList<String>
    private lateinit var alcohol: Alcohol
    private var scrapStatus = false
    private lateinit var alcoholDetailReviewRecyclerViewAdapter: AlcoholDetailReviewRecyclerViewAdapter
    private lateinit var alcoholDetailPostReviewRecyclerViewAdapter: AlcoholDetailPostRecyclerViewAdapter
    private lateinit var alcoholDetailRecommendRecyclerViewAdapter: AlcoholDetailPostRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlcoholdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //데이터 불러오기
        loadData()
        //툴바 적용하기
        initActionBar()
    }

    private fun loadData() {
        val extras = intent.extras
        if (extras != null) {
            alcoholId = extras.getLong("alcoholId")
        }
        CommonService.findAlcoholDetail(alcoholId, 1)
            .enqueue(object : Callback<AlcoholDetailResponse> {
                override fun onResponse(
                    call: Call<AlcoholDetailResponse>,
                    response: Response<AlcoholDetailResponse>
                ) {
                    if (response.code() == 200) {
                        val body = response.body()
                        if (body != null) {
                            reviews = body.data.reviews ?: listOf()
                            posts = body.data.recommendationPosts ?: listOf()
                            recommendationPosts = body.data.recommendationPosts ?: listOf()
                            alcohol = body.data.alcohol
                            scrapStatus = body.data.scrapStatus
                            alcoholInformation = arrayListOf()
                            if (alcohol.type != null) {
                                alcoholInformation.add(alcohol.type!!)
                            }
                            if (alcohol.food != null) {
                                alcoholInformation.add(alcohol.food!!)
                            }
                            if (alcohol.flavor != null) {
                                alcoholInformation.add(alcohol.flavor!!)
                            }
                            if (alcohol.volume != null) {
                                alcoholInformation.add(alcohol.volume + "도")
                            }
                            //scrapStatus = body.data.//스크랩상태변수
                            //뷰에 데이터 적용하기
                            setDataOnView()
                            //리사이클러뷰 설정하기
                            initRecyclerView()
                            //리스너 설정하기
                            initListener()
                        }
                    } else {
                        Log.e("AlcoholDetail", "response code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<AlcoholDetailResponse>, t: Throwable) {
                    Log.e("AlcoholDetail", "error: ${t.localizedMessage}")
                }

            })
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarAlcoholdetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun setDataOnView() {
        binding.textviewAlcoholdetailName.text = alcohol.name
        binding.textviewAlcoholdetailPrice.text = "예상 가격 ${alcohol.price}원(${alcohol.size}ml)"
        binding.textviewAlcoholdetailContent.text = alcohol.info ?: "상세 정보가 없습니다."
        if (!scrapStatus) {
            binding.imageviewAlcoholdetailScrap.setImageResource(R.drawable.all_icon_scrap24)
        } else {
            binding.imageviewAlcoholdetailScrap.setImageResource(R.drawable.all_icon_scrap24_full)
        }
    }

    private fun initRecyclerView() {
        alcoholDetailReviewRecyclerViewAdapter =
            AlcoholDetailReviewRecyclerViewAdapter(reviews).apply {
                onItemLongClickListener(object :
                    AlcoholDetailReviewRecyclerViewAdapter.OnItemLongClickListener {
                    override fun onItemLongClick(
                        v: View,
                        item: Review
                    ): Boolean? {
                        val builder = AlertDialog.Builder(this@AlcoholDetailActivity)
                        builder.setMessage("리뷰를 삭제하시겠습니까?")
                            .setNegativeButton("취소", DialogInterface.OnClickListener { dialog, id ->
                                Snackbar.make(v, "취소했습니다.", Snackbar.LENGTH_SHORT).show()
                            })
                            .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, id ->
                                CommonService.deleteReview(item.alcoholId, item.id)
                                    .enqueue(object : Callback<DefaultResponse> {
                                        override fun onResponse(
                                            call: Call<DefaultResponse>,
                                            response: Response<DefaultResponse>
                                        ) {
                                            if (response.code() == 200) {
                                                Snackbar.make(v, "삭제했습니다.", Snackbar.LENGTH_SHORT)
                                                    .show()
                                                loadData()
                                            } else {
                                                Snackbar.make(
                                                    v,
                                                    "오류가 발생했습니다.",
                                                    Snackbar.LENGTH_SHORT
                                                ).show()
                                            }

                                        }

                                        override fun onFailure(
                                            call: Call<DefaultResponse>,
                                            t: Throwable
                                        ) {
                                            Snackbar.make(v, "오류가 발생했습니다.", Snackbar.LENGTH_SHORT)
                                                .show()
                                        }

                                    })

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
        if (reviews.isNotEmpty()) {
            binding.textviewAlcoholdetailShortreviewplaceholder.visibility = View.GONE
            binding.recyclerviewAlcoholdetailReview.visibility = View.VISIBLE
        }
        if (posts.isNotEmpty()) {
            binding.textviewAlcoholdetailPostreviewplaceholder.visibility = View.GONE
            binding.recyclerviewAlcoholdetailPostreview.visibility = View.VISIBLE
            alcoholDetailPostReviewRecyclerViewAdapter =
                AlcoholDetailPostRecyclerViewAdapter(posts)
            binding.recyclerviewAlcoholdetailPostreview.adapter =
                alcoholDetailPostReviewRecyclerViewAdapter.apply {
                    onItemClickListener(object : OnItemClickListener {
                        override fun onItemClick(v: View, item: Post) {

                        }
                    })
                }

        }
        if (recommendationPosts.isNotEmpty()) {
            binding.recyclerviewAlcoholdetailRecommend.visibility = View.VISIBLE
            alcoholDetailRecommendRecyclerViewAdapter =
                AlcoholDetailPostRecyclerViewAdapter(recommendationPosts)
            binding.recyclerviewAlcoholdetailRecommend.adapter =
                alcoholDetailRecommendRecyclerViewAdapter.apply {
                    onItemClickListener(object : OnItemClickListener {
                        override fun onItemClick(v: View, item: Post) {

                        }

                    })
                }

        }
        if (alcoholInformation.isNotEmpty()) {
            binding.recyclerviewAlcoholdetailAlcoholinformation.adapter =
                AlcoholDetailAlcoholInformationRecyclerViewAdapter(alcoholInformation)
        }
    }

    private fun initListener() {
        binding.imageviewAlcoholdetailScrap.setOnClickListener {
            CommonService.requestScrap(alcoholId, 1).enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.code() == 200) {
                        if (!scrapStatus) {
                            binding.imageviewAlcoholdetailScrap.setImageResource(R.drawable.all_icon_scrap24_full)
                            scrapStatus = true
                        } else {
                            binding.imageviewAlcoholdetailScrap.setImageResource(R.drawable.all_icon_scrap24)
                            scrapStatus = false
                        }
                    } else {
                        Log.e("AlcoholDetail", "response code : ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.e("AlcoholDetail", "error : ${t.localizedMessage}")
                }

            })
        }
        binding.textviewAlcoholdetailWriteshortreview.setOnClickListener {
            val intent = Intent(applicationContext, AlcoholDetailWriteReviewActivity::class.java)
            intent.putExtra("name", alcohol.name)
            intent.putExtra("alcoholId", alcohol.id ?: alcoholId)
            intent.putExtra(
                "information",
                "${alcohol.price}원 · ${alcohol.size}ml · ${alcohol.volume}%"
            )
            startActivity(intent)
        }
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

    override fun onResume() {
        super.onResume()
        loadData()
    }
}