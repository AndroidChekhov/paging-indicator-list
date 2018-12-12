package com.androidchekhov.pagination

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A [RecyclerView] implementation that makes sure the paging view fills the width of the recycler view regardless of
 * span count. If a [GridLayoutManager] is not used, this implementation is unnecessary (although it won't hurt).
 */
open class PagingRecyclerView : RecyclerView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        (layoutManager as? GridLayoutManager)?.let {
            it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter?.getItemViewType(position)) {
                        PagingAdapter.PAGING_VIEW_TYPE -> it.spanCount
                        else -> 1
                    }
                }
            }
        }
    }

    /**
     * Gets the paging view type. This is exposed in case [GridLayoutManager.setSpanSizeLookup] needs to be defined
     * elsewhere, in which case the look up should set the span count for this type.
     */
    fun getPagingViewType(): Int = PagingAdapter.PAGING_VIEW_TYPE
}