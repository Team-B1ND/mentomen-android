package kr.hs.dgsw.mentomenv2.adapter

import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentAdapterCallback
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.CommentSettingFragmentBinding
import kr.hs.dgsw.mentomenv2.databinding.ItemCommentBinding
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.feature.detail.comment.CommentViewModel
import javax.inject.Inject

class CommentAdapter(
    private val callback: CommentAdapterCallback
) :
    BaseListAdapter<Comment, ItemCommentBinding>(R.layout.item_comment, CommentDiffUtil) {
    private var myUserId = 0

    fun setMyUserId(userId: Int) {
        this.myUserId = userId
        notifyDataSetChanged()
    }

    override fun action(item: Comment, binding: ItemCommentBinding) {
        binding.item = item
        val bottomSheetBinding =
            CommentSettingFragmentBinding.inflate(LayoutInflater.from(binding.root.context))
        val bottomSheetDialog = BottomSheetDialog(binding.root.context)
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.AnimationPopupStyle
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        binding.btnMore.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetBinding.tvCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.tvDelete.setOnClickListener {
            callback.deleteComment(item.commentId.toInt())
            bottomSheetDialog.dismiss()
        }

        binding.btnMore.visibility = if (item.userId == myUserId) View.VISIBLE else View.GONE

//        bottomSheetBinding.tvEdit.setOnClickListener {
//            callback.editComment(item.commentId.toInt())
//            bottomSheetDialog.dismiss()
//        }
    }
}
