package com.androidchekhov.pagination

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class PagingAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    itemCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, VH>(itemCallback) {

    abstract fun getViewType(pos: Int) : Int

    abstract fun onCreatePagingViewHolder(parent: ViewGroup): VH

    abstract fun onCreateDataViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract fun onBindDataViewHolder(holder: VH, pos: Int)

    var isPaging : Boolean = false
        set(value) {
            field = value

            notifyItemChanged(itemCount)
        }

    override fun getItemViewType(position: Int): Int {
        return when(isPaging && isPositionOfPagingView(position)) {
            true -> PAGING_VIEW_TYPE
            false -> getViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when(viewType.equals(PAGING_VIEW_TYPE)) {
            true -> onCreatePagingViewHolder(parent)
            false -> onCreateDataViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (!isPositionOfPagingView(position)) { onBindDataViewHolder(holder, position) }
    }

    override fun getItemCount(): Int {
        val count = currentList?.size ?: 0

        return when (isPaging) {
            true -> count + 1
            false -> count
        }
    }

    private fun isPositionOfPagingView(pos: Int) : Boolean = pos == currentList?.size

    companion object {
        const val PAGING_VIEW_TYPE = -1
    }
}