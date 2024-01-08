package kr.hs.dgsw.mentomenv2.adapter

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.CommentDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemCommentBinding
import kr.hs.dgsw.mentomenv2.databinding.ItemHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Comment

class CommentAdapter :
    BaseListAdapter<Comment, ItemCommentBinding>(R.layout.item_comment, CommentDiffUtil) {

    override fun action(item: Comment, binding: ItemCommentBinding) {
        binding.item = item
        val inflater = binding.root.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val bottomSheetView = inflater.inflate(R.layout.comment_setting_fragment, null)
        val bottomSheetDialog = BottomSheetDialog(binding.root.context)
        bottomSheetDialog.setContentView(bottomSheetView)
        binding.btnMore.setOnClickListener {
            bottomSheetDialog.show()
        }
    }
}
