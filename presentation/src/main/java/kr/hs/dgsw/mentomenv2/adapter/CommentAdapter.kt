package kr.hs.dgsw.mentomenv2.adapter

import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemCommentBinding
import kr.hs.dgsw.mentomenv2.databinding.ItemHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Comment

class CommentAdapter(
    private val itemClick: (Comment) -> Unit,
) :
    BaseListAdapter<Comment, ItemCommentBinding>(R.layout.item_comment, CommentDiffUtil) {

    override fun action(item: Comment, binding: ItemCommentBinding) {
        binding.item = item
        binding.root.setOnClickListener {
            itemClick(item)
        }
    }
}
