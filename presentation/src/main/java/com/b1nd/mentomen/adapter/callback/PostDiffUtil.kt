package com.b1nd.mentomen.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.b1nd.mentomen.domain.model.Post

object PostDiffUtil : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(
        oldItem: Post,
        newItem: Post,
    ) = oldItem.postId == newItem.postId

    override fun areContentsTheSame(
        oldItem: Post,
        newItem: Post,
    ) = oldItem.content == newItem.content
}
