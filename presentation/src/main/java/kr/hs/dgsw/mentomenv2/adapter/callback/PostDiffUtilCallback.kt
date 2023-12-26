package kr.hs.dgsw.mentomenv2.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.mentomenv2.domain.model.Post

object PostDiffUtilCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(
        oldItem: Post,
        newItem: Post,
    ) = oldItem.postId == newItem.postId

    override fun areContentsTheSame(
        oldItem: Post,
        newItem: Post,
    ) = oldItem == newItem
}
