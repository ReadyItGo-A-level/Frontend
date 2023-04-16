package com.example.a_level.feed

import android.app.Activity
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.a_level.App
import com.example.a_level.R
import com.example.a_level.common.OnSwipeTouchListener
import com.example.a_level.databinding.ActivityFeeddetailBinding
import com.example.a_level.feed.model.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class FeedDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityFeeddetailBinding
    private var id: Long = 0
    private lateinit var postDetail: FeedDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeeddetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //데이터 불러오기
        loadData()
        //툴바 적용하기
        initActionBar()
        //기타 UI 설정하기
        initUI()
        //댓글 올리기
        sendComment()
    }

    private fun loadData() {
        id = intent.getLongExtra("id", 0)

        FeedService.retrofitGetFeedDetail(id).enqueue(object: Callback<FeedDetailResponse> {
            override fun onResponse(
                call: Call<FeedDetailResponse>,
                response: Response<FeedDetailResponse>
            ) {
                if(response.isSuccessful){
                    Log.e("log", response.toString())
                    Log.e("log", response.body().toString())

                    var data=response.body()?.data
                    var url="http://13.125.232.247:8080"

                    if(data!=null) {
                        var id = data.id
                        var username = data.username
                        var title = data.title
                        var content = data.content
                        var image = if(data.image==null) "" else url+data.image
                        var hit = data.hit
                        var commentCount = data.commentCount
                        var scrapCount = data.scrapCount
                        var likeCount = data.likeCount
                        var alcoholName = data.alcoholName
                        var alcoholType = if(data.alcoholType==null) "" else data.alcoholType
                        var flavor = data.flavor
                        var volume = if (data.volume == null) "" else data.volume
                        var price = data.price
                        var body = data.body
                        var sugar = data.sugar
                        var modifiedDate = data.modifiedDate
                        var like=data.like
                        var scrap=data.scrap
                        var comments=if(data.comments==null) arrayListOf<Comments>() else data.comments

                        postDetail=FeedDetail(id,username,title, content, image,hit,commentCount,scrapCount,likeCount,alcoholName,alcoholType,flavor,volume,price, body,sugar,modifiedDate,like,scrap,comments)
                    }

                    //뷰에 데이터 적용하기
                    setDataOnView()
                    //리사이클러뷰 설정하기
                    initRecyclerView()
                }else {
                    try {
                        val body = response.errorBody()!!.string()

                        Log.e(ContentValues.TAG, "body : $body")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<FeedDetailResponse>, t: Throwable) {
                Log.e("TAG", "실패원인: {$t}")
            }
        })

    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarFeeddetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun setDataOnView() {
        binding.textviewFeeddetailUsername.text = postDetail.username
        binding.textviewFeeddetailTitle.text = postDetail.title
        binding.textviewFeeddetailContent.text = postDetail.content
        var dateString = postDetail.modifiedDate
        val dateStr = dateString.substringBefore("T")

        if(postDetail.image!=""){
            binding.constraintlayoutFeeddetail.visibility=View.VISIBLE
            Glide.with(this)
                .load(postDetail.image)
                .fitCenter()
                .error(R.drawable.common_alcohol_test)
                .apply(RequestOptions().override(500, 500))
                .into(binding.imageviewFeeddetail)
        }

        binding.textviewFeeddetailDate.text = dateStr
        binding.textviewFeeddetailLikecount.text = postDetail.likeCount.toString()
        binding.textviewFeeddetailCommentcount.text = postDetail.commentCount.toString()
        binding.textviewFeeddetailScrapcount.text = postDetail.scrapCount.toString()
    }

    private fun initRecyclerView() {
        val feedInformationData = ArrayList<Pair<String, String>>()
//        postDetail.alcoholType?.let{ feedInformationData.add(Pair("주종", postDetail.alcoholType!!))}
        postDetail.alcoholName?.let { feedInformationData.add(Pair("이름", postDetail.alcoholName!!)) }
        if(postDetail.volume!="") { feedInformationData.add(Pair("도수", postDetail.volume.toString())) }
        if(postDetail.sugar!=null) { feedInformationData.add(Pair("당도", postDetail.sugar.toString())) }
        if(postDetail.price!="") { feedInformationData.add(Pair("예상가격", postDetail.price.toString())) }
        if(postDetail.flavor!="") { feedInformationData.add(Pair("맛", postDetail.flavor!!)) }
        binding.recyclerviewFeeddetailInformation.adapter = FeedDetailInformationAdapter(feedInformationData)

        binding.recyclerviewFeeddetailComment.adapter = FeedDetailCommentAdapter(postDetail.comments)
    }

    private fun initUI() {
        setOnSwipeListener(binding.nestedscrollviewFeeddetail)


        /* 로티 이용해 이미지 꾹 누르면 좋아요 애니메이션 발동하는 코드
        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
            }
            override fun onAnimationEnd(animator: Animator) {
                binding.lottieView.visibility = View.GONE
            }
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
        binding.imageviewFeeddetailPhoto.setOnLongClickListener {
            Log.d("test lottie", "click")
            binding.lottieView.visibility = View.VISIBLE;
            binding.lottieView.playAnimation();
            return@setOnLongClickListener (true)
        }
        */
    }

    private fun sendComment(){
        binding.imageviewFeeddetailSend.setOnClickListener {
            if(binding.edittextFeeddetailComment.text.toString()==""){
                Toast.makeText(this,"댓글을 적어주세요.",Toast.LENGTH_SHORT).show()
            }
            else{
                var userId= App.prefs.getString("userId","")
                var comment=binding.edittextFeeddetailComment.text.toString()
                FeedService.retrofitPostComment(id,CommentRequest(userId.toLong(),comment)).enqueue(object: Callback<CommentResponse>{
                    override fun onResponse(
                        call: Call<CommentResponse>,
                        response: Response<CommentResponse>
                    ) {
                        if(response.isSuccessful){
                            Log.e("log", response.toString())
                            Log.e("log", response.body().toString())
                        }else {
                            try {
                                val body = response.errorBody()!!.string()

                                Log.e(ContentValues.TAG, "body : $body")
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }

                    }

                    override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                        Log.e("TAG", "실패원인: {$t}")
                    }
                })
            }
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

    //스와이프로 뒤로가기
    private fun Activity.setOnSwipeListener(vararg views: View) {
        val onSwipeTouchListener =
            OnSwipeTouchListener(this, object : OnSwipeTouchListener.OnSwipeCallback {
                override fun onLeft() {}
                override fun onRight() {
                    finish()
                    overridePendingTransition(
                        R.anim.anim_enter_no_change,
                        R.anim.anim_exit_slide_right
                    )
                }
            })
        views.forEach { view ->
            if (view is RecyclerView) {
                view.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean =
                        onSwipeTouchListener.getGestureDetector().onTouchEvent(e)

                    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

                })
            } else {
                view.setOnTouchListener(onSwipeTouchListener)
            }
        }
        window.decorView.rootView.setOnTouchListener(onSwipeTouchListener)
    }
}