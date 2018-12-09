package com.androidchekhov.pagingrecyclerview.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidchekhov.pagination.PagingAdapter
import com.androidchekhov.pagingrecyclerview.R
import com.androidchekhov.pagingrecyclerview.domain.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsPagingAdapter: PagingAdapter<Comment, RecyclerView.ViewHolder>(CommentsDiffCallback()) {

    override fun getViewType(pos: Int): Int = 1

    override fun onCreatePagingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = inflate(parent, R.layout.item_loading)

        return PagingViewHolder(view)
    }

    override fun onCreateDataViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflate(parent, R.layout.item_comment)

        return CommentViewHolder(view)
    }
    override fun onBindDataViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
            getItem(pos)?.let {
                with(holder.itemView) {
                    username.text = it.username
                    displayDate.text = it.displayDate
                    commentText.text = it.comment
                }
            }
    }

    private fun inflate(parent: ViewGroup, layoutRes: Int): View {
        val inflater = LayoutInflater.from(parent.context)

        return inflater.inflate(layoutRes, parent, false)
    }

    class CommentViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView)

    class PagingViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView)
}