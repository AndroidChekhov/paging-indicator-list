package com.androidchekhov.pagination

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 *  An abstract [PagedListAdapter] that adds and removes a paging view to indicate that a new page is being loaded, as
 *  [isPaging] is updated.
 */
abstract class PagingAdapter<T : Any, VH : RecyclerView.ViewHolder>(
        itemCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, VH>(itemCallback) {

    var isPaging: Boolean = false
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    /**
     * Get view type at position [pos]. Equivalent to [getItemViewType].
     */
    abstract fun getViewType(pos: Int): Int

    /**
     * Create the paging view holder.
     */
    abstract fun onCreatePagingViewHolder(parent: ViewGroup): VH

    /**
     * Create any view holders here. Equivalent to [onCreateViewHolder].
     */
    abstract fun onCreateDataViewHolder(parent: ViewGroup, viewType: Int): VH

    /**
     * Bind data to view holders.  Equivalent to [onBindDataViewHolder]. Note, this will never be called for the paging
     * view holder created in [onCreatePagingViewHolder].
     */
    abstract fun onBindDataViewHolder(holder: VH, pos: Int)

    final override fun getItemViewType(position: Int): Int {
        return when (isPaging && isPositionOfPagingView(position)) {
            true -> PAGING_VIEW_TYPE
            false -> getViewType(position)
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (viewType == PAGING_VIEW_TYPE) {
            true -> onCreatePagingViewHolder(parent)
            false -> onCreateDataViewHolder(parent, viewType)
        }
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        if (!isPositionOfPagingView(position)) {
            onBindDataViewHolder(holder, position)
        }
    }

    final override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        onBindViewHolder(holder, position)
    }

    final override fun getItemCount(): Int {
        val count = currentList?.size ?: 0

        return when (isPaging) {
            true -> count + 1
            false -> count
        }
    }

    private fun isPositionOfPagingView(pos: Int): Boolean = pos == currentList?.size

    companion object {
        const val PAGING_VIEW_TYPE = -1
    }
}