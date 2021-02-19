package com.jgodinez.dotsindicator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.slider_item.view.*

class SlideAdapter(private val items: List<SlideItem>) : PagerAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val context = container.context
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.slider_item, container, false)

        val item: SlideItem = items[position]
        with(layout) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, item.image))
            textViewDescription.text = item.description
        }
        container.addView(layout)

        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }
}