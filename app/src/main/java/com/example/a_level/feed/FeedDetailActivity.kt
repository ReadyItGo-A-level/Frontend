package com.example.a_level.feed

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R
import com.example.a_level.common.OnSwipeTouchListener
import com.example.a_level.databinding.ActivityFeeddetailBinding
import com.example.a_level.feed.model.response.Comment
import com.example.a_level.feed.model.response.PostDetail


class FeedDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityFeeddetailBinding
    private var id: Long = 0
    private lateinit var postDetail: PostDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeeddetailBinding.inflate(layoutInflater)
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
        id = intent.getLongExtra("id", 0)
        val comments = ArrayList<Comment>()
        comments.add(Comment("익명이", "오~ 대박~", "0000-00-00"))
        postDetail = PostDetail(
            id,
            "알랑방구",
            "제목 뭘로 짓냐",
            "제곧내",
            null,
            null,
            "까르띠에 소비뇽 500년산",
            "달콤",
            null,
            "2,000,000",
            null,
            null,
            null,
            3,
            10,
            50,
            comments
        )
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarFeeddetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun setDataOnView() {
        binding.textviewFeeddetailUsername.text = postDetail.userName
        binding.textviewFeeddetailTitle.text = postDetail.title
        binding.textviewFeeddetailContent.text = postDetail.content
        binding.textviewFeeddetailDate.text = postDetail.modifiedDate
        binding.textviewFeeddetailLikecount.text = postDetail.likeCount.toString()
        binding.textviewFeeddetailCommentcount.text = postDetail.commentCount.toString()
        binding.textviewFeeddetailScrapcount.text = postDetail.scrapCount.toString()
    }

    private fun initRecyclerView() {
        val feedInformationData = ArrayList<Pair<String, String>>()
        postDetail.alcoholName?.let { feedInformationData.add(Pair("이름", postDetail.alcoholName!!)) }
        postDetail.volume?.let { feedInformationData.add(Pair("도수", postDetail.volume.toString())) }
        postDetail.sugar?.let { feedInformationData.add(Pair("당도", postDetail.sugar.toString())) }
        postDetail.price?.let { feedInformationData.add(Pair("예상가격", postDetail.price.toString())) }
        postDetail.flavor?.let { feedInformationData.add(Pair("맛", postDetail.flavor!!)) }
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