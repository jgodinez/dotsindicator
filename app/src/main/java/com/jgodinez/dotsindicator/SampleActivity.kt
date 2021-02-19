package com.jgodinez.dotsindicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val slideItems = listOf(
            SlideItem(
                getString(R.string.sample_item_description_bike),
                R.drawable.ic_baseline_directions_bike
            ),
            SlideItem(
                getString(R.string.sample_item_description_bus),
                R.drawable.ic_baseline_directions_bus
            ),
            SlideItem(
                getString(R.string.sample_item_description_car),
                R.drawable.ic_baseline_directions_car
            )
        )

        viewPager.adapter = SlideAdapter(slideItems)

        horizontalIndicator.attachViewPager(viewPager)
        verticalIndicator.attachViewPager(viewPager)
        defaultIndicator.attachViewPager(viewPager)
    }
}