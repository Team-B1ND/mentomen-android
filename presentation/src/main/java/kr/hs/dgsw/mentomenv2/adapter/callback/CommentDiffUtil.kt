package kr.hs.dgsw.mentomenv2.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.mentomenv2.domain.model.Comment

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
