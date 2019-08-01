package io.github.brunogabriel.doggieapp.shared.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by brunogabriel on 2019-06-15.
 */
class NonSwipeViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    override fun onInterceptTouchEvent(ev: MotionEvent?) = false
    override fun onTouchEvent(ev: MotionEvent?) = false
}