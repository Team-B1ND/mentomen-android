package com.b1nd.mentomen.adapter

import android.view.LayoutInflater
import android.view.View
import com.b1nd.mentomen.R
import com.b1nd.mentomen.adapter.callback.CommentAdapterCallback
import com.b1nd.mentomen.adapter.callback.CommentDiffUtil
import com.b1nd.mentomen.base.BaseListAdapter
import com.b1nd.mentomen.databinding.DialogCommentBinding
import com.b1nd.mentomen.databinding.ItemCommentBinding
import com.b1nd.mentomen.domain.model.Comment
import com.b1nd.mentomen.util.dpToPx
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.math.roundToInt

class CommentAdapter(
    private val callback: CommentAdapterCallback,
) : BaseListAdapter<Comment, ItemCommentBinding>(R.layout.item_comment, CommentDiffUtil) {
    var userId: Int = 0

    override fun action(
        item: Comment,
        binding: ItemCommentBinding,
    ) {
        binding.item = item
        val bottomSheetBinding =
            DialogCommentBinding.inflate(LayoutInflater.from(binding.root.context))
        val bottomSheetDialog = BottomSheetDialog(binding.root.context)
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.AnimationPopupStyle
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        binding.btnMore.visibility = if (userId == item.userId) View.VISIBLE else View.GONE

        binding.cvComment.post {
            binding.tvTime.post {
                binding.content.maxWidth =
                    binding.cvComment.width - (
                        binding.tvTime.width + binding.ivProfile.width +
                            dpToPx(
                                binding.content.context,
                                80.0f,
                            ).roundToInt()
                    )
            }
        }

        binding.btnMore.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetBinding.tvCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
            callback.updateIsEdit()
        }

        bottomSheetBinding.tvDelete.setOnClickListener {
            callback.deleteComment(item.commentId.toInt())
            callback.updateIsEdit()
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.tvEdit.setOnClickListener {
            bottomSheetDialog.dismiss()
            callback.updateIsEdit(true, item.commentId.toInt(), item.content)
        }

        binding.btnMore.setOnClickListener {
            bottomSheetDialog.show()
        }
    }
}
