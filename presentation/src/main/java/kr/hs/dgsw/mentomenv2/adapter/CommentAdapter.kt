package kr.hs.dgsw.mentomenv2.adapter

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.CommentSettingFragmentBinding
import kr.hs.dgsw.mentomenv2.databinding.ItemCommentBinding
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.feature.detail.comment.CommentViewModel

class CommentAdapter :
    BaseListAdapter<Comment, ItemCommentBinding>(R.layout.item_comment, CommentDiffUtil) {
    override fun action(item: Comment, binding: ItemCommentBinding) {
        binding.item = item

        val inflater =
            binding.root.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val bottomSheetView = inflater.inflate(R.layout.comment_setting_fragment, null)
        val bottomSheetBinding =
            CommentSettingFragmentBinding.inflate(LayoutInflater.from(binding.root.context))
        val bottomSheetDialog = BottomSheetDialog(binding.root.context)

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetDialog.setContentView(bottomSheetView)
        binding.btnMore.setOnClickListener {
            bottomSheetDialog.show()
        }
        bottomSheetBinding.tvCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetBinding.tvDelete.setOnClickListener {
//            commentViewModel.deleteComment(item.commentId.toInt())
        }
    }
}
