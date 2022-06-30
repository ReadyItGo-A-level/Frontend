package com.example.a_level.common

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class OnSwipeTouchListener(context: Context, callback: OnSwipeCallback) : View.OnTouchListener {
    private val gestureDetector: GestureDetector =
        GestureDetector(context, OnSwipeGestureListener(callback))

    companion object {
        private val SWIPE_THRESHOLD = 500
        private val SWIPE_VELOCITY_THRESHOLD = 200
    }

    fun getGestureDetector() = gestureDetector
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        try {
            return gestureDetector.onTouchEvent(event)
        } catch (e: Exception) {
            Log.d("OnSwipeTouchListener", "Error : $e")
        }
        return false
    }

    interface OnSwipeCallback {
        fun onLeft()
        fun onRight()
    }

    class OnSwipeGestureListener(val callback: OnSwipeCallback) :
        GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean = false

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            try {
                if (e2 == null || e1 == null) return false
                val diffX = e2.x - e1.x
                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        callback.onRight()
                    } else {
                        callback.onLeft()
                    }
                    return true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }
    }
}