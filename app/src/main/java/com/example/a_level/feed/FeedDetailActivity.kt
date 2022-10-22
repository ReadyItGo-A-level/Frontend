package com.example.a_level.feed

import android.animation.Animator
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R
import com.example.a_level.common.OnSwipeTouchListener
import com.example.a_level.databinding.ActivityFeeddetailBinding


class FeedDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityFeeddetailBinding

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

    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarFeeddetail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun setDataOnView() {

    }

    private fun initRecyclerView() {

    }

    private fun initUI() {
        setOnSwipeListener(binding.nestedscrollviewFeeddetail)
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
            Log.d("zopal", "click")
            binding.lottieView.visibility = View.VISIBLE;
            binding.lottieView.playAnimation();
            return@setOnLongClickListener (true)
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

    fun Activity.setOnSwipeListener(vararg views: View) {
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