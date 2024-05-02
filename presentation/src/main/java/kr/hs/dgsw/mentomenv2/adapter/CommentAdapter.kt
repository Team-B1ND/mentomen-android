package kr.hs.dgsw.mentomenv2.adapter

import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentAdapterCallback
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.DialogCommentBinding
import kr.hs.dgsw.mentomenv2.databinding.ItemCommentBinding
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.util.dpToPx
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
