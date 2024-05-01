package kr.hs.dgsw.mentomenv2.adapter

import android.view.View
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.NoticeDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemNoticeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Notice

class NoticeAdapter(
    private val itemClick: (Int) -> Unit,
) : BaseListAdapter<Notice, ItemNoticeBinding>(R.layout.item_notice, NoticeDiffUtil) {
    override fun action(item: Notice, binding: ItemNoticeBinding) {
        binding.root.setOnClickListener {
            itemClick(item.postId)
        }
        when (item.noticeStatus) {
            "NONE" -> binding.status.visibility = View.GONE
            "EXIST" -> binding.status.visibility = View.VISIBLE
        }
    }
}