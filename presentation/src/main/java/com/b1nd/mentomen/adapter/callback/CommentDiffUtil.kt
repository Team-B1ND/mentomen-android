package com.b1nd.mentomen.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.b1nd.mentomen.domain.model.Comment

object CommentDiffUtil : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(
        oldItem: Comment,
        newItem: Comment,
    ) = oldItem.commentId == newItem.commentId

    override fun areContentsTheSame(
        oldItem: Comment,
        newItem: Comment,
    ) = oldItem == newItem
}
