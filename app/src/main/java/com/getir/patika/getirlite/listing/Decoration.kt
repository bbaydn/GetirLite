package com.getir.patika.getirlite.listing

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class Decoration(private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (includeEdge) {
            outRect.bottom = spacing // item bottom
        }
    }
}
