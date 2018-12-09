package com.androidchekhov.pagingrecyclerview.presentation

import androidx.recyclerview.widget.DiffUtil
import com.androidchekhov.pagingrecyclerview.domain.Comment

class CommentsDiffCallback : DiffUtil.ItemCallback<Comment>() {

    override fun areItemsTheSame(
            oldItem: Comment,
            newItem: Comment
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
            oldItem: Comment,
            newItem: Comment
    ) = oldItem == newItem
}