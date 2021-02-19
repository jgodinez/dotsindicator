package com.jgodinez.dotsindicator

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

private const val DEFAULT_INDICATOR_WIDTH = 8

class DotsIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private lateinit var viewPager: ViewPager

    private var indicatorMargin = -1
    private var indicatorWidth = -1
    private var indicatorHeight = -1
    private var indicatorOrientation = HORIZONTAL
    private var indicatorBackgroundResId: Int = 0
    private var indicatorUnselectedBackgroundResId: Int = 0
    private var lastPosition = -1

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DotsIndicator)

        try {
            indicatorWidth = typedArray.getDimensionPixelSize(R.styleable.DotsIndicator_dot_width, -1)
            indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.DotsIndicator_dot_height, -1)
            indicatorMargin = typedArray.getDimensionPixelSize(R.styleable.DotsIndicator_dot_margin, -1)
            indicatorOrientation = typedArray.getInt(R.styleable.DotsIndicator_dot_orientation, HORIZONTAL)
            indicatorBackgroundResId = typedArray.getResourceId(R.styleable.DotsIndicator_dot_drawable_selected, R.drawable.selected_dot)
            indicatorUnselectedBackgroundResId = typedArray.getResourceId(R.styleable.DotsIndicator_dot_drawable_unselected, R.drawable.unselected_dot)
        } finally {
            typedArray.recycle()
        }

        val miniSize = (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            DEFAULT_INDICATOR_WIDTH.toFloat(),
            resources.displayMetrics
        ) + 0.5f).toInt()
        indicatorWidth = indicatorWidth.greaterThanZeroOrDefault(miniSize)
        indicatorHeight = indicatorHeight.greaterThanZeroOrDefault(miniSize)
        indicatorMargin = indicatorMargin.greaterThanZeroOrDefault(miniSize)

        orientation = indicatorOrientation
        gravity = Gravity.CENTER

        if (isInEditMode) {
            createIndicators(3)
        }
    }

    fun attachViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        if (this.viewPager.adapter != null) {
            lastPosition = -1
            createIndicators()
            this.viewPager.removeOnPageChangeListener(internalPageChangeListener)
            this.viewPager.addOnPageChangeListener(internalPageChangeListener)
            internalPageChangeListener.onPageSelected(this.viewPager.currentItem)
        }
    }

    private fun createIndicators(count: Int) {
        val currentItem = if (isInEditMode) {
            0
        } else {
            viewPager.currentItem
        }
        for (i in 0 until count) {
            if (currentItem == i) addIndicator(indicatorBackgroundResId)
            else addIndicator(indicatorUnselectedBackgroundResId)
        }
    }

    private fun createIndicators() {
        removeAllViews()
        val adapter = viewPager.adapter
        val count = adapter?.count ?: 0
        if (count <= 0) return
        createIndicators(count)
    }

    private fun addIndicator(@DrawableRes drawableRes: Int) {
        val indicator = View(context)
        indicator.background = ContextCompat.getDrawable(context, drawableRes)
        addView(indicator, indicatorWidth, indicatorHeight)
        val layoutParams = indicator.layoutParams as LayoutParams
        layoutParams.apply {
            if (indicatorOrientation == HORIZONTAL) {
                leftMargin = indicatorMargin
                rightMargin = indicatorMargin
            } else {
                topMargin = indicatorMargin
                bottomMargin = indicatorMargin
            }
        }
    }

    private val internalPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            if ((viewPager.adapter?.count ?: 0) <= 0) return
            if (lastPosition >= 0) getChildAt(lastPosition)?.setBackgroundResource(indicatorUnselectedBackgroundResId)
            getChildAt(position)?.setBackgroundResource(indicatorBackgroundResId)
            lastPosition = position
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

        override fun onPageScrollStateChanged(state: Int) = Unit
    }

    private fun Int.greaterThanZeroOrDefault(default: Int): Int {
        return if (this < 0) default else this
    }
}