package com.b1nd.mentomen.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.b1nd.mentomen.domain.model.Notice

object NoticeDiffUtil : DiffUtil.ItemCallback<Notice>() {
    override fun areItemsTheSame(
        oldItem: Notice,
        newItem: Notice,
    ): Boolean = oldItem.noticeStatus == newItem.noticeStatus && oldItem.postId == newItem.postId

    override fun areContentsTheSame(
        oldItem: Notice,
        newItem: Notice,
    ): Boolean = oldItem.commentContent == newItem.commentContent && oldItem.postId == newItem.postId
}
