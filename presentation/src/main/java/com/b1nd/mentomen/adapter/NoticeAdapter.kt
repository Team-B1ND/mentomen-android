package com.b1nd.mentomen.adapter

import android.view.View
import com.b1nd.mentomen.R
import com.b1nd.mentomen.adapter.callback.NoticeDiffUtil
import com.b1nd.mentomen.base.BaseListAdapter
import com.b1nd.mentomen.databinding.ItemNoticeBinding
import com.b1nd.mentomen.domain.model.Notice

class NoticeAdapter(
    private val itemClick: (Int) -> Unit,
) : BaseListAdapter<Notice, ItemNoticeBinding>(R.layout.item_notice, NoticeDiffUtil) {
    override fun action(
        item: Notice,
        binding: ItemNoticeBinding,
    ) {
        binding.item = item
        binding.root.setOnClickListener {
            itemClick(item.postId)
        }
        when (item.noticeStatus) {
            "NONE" -> binding.status.visibility = View.GONE
            "EXIST" -> binding.status.visibility = View.VISIBLE
        }
    }
}
