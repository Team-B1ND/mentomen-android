package kr.hs.dgsw.mentomenv2.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.PostDiffUtilCallback
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post

class HomeAdapter(
    private val itemClick: (Post) -> Unit,
) :
    BaseListAdapter<Post, ItemHomeBinding>(R.layout.item_home, PostDiffUtilCallback) {
    @SuppressLint("SetTextI18n")
    override fun action(
        item: Post,
        binding: ItemHomeBinding,
    ) {
        binding.item = item
        Log.d("action: ", "${item.isExpended} + ${item.content}")
        if (item.isExpended) {
            binding.tvPreview.maxLines = Int.MAX_VALUE
            binding.btnShowMore.visibility = View.GONE
        } else {
            binding.tvPreview.maxLines = 3
            binding.tvPreview.post {
                val layout = binding.tvPreview.layout

                // 마지막 줄이 완전히 보이지 않는 경우에는 "..."이 있는 것으로 판단
                val lastLineVisible = layout.getEllipsisCount(layout.lineCount - 1) == 0

                if (!lastLineVisible) {
                    binding.btnShowMore.visibility = View.VISIBLE
                } else {
                    binding.btnShowMore.visibility = View.GONE
                }
            }
        }
        if (!item.imgUrls.isNullOrEmpty()) {
            binding.ivPreview.visibility = View.VISIBLE
            Glide.with(binding.ivPreview.context)
                .load(item.imgUrls?.first())
                .into(binding.ivPreview)
        } else {
            binding.ivPreview.visibility = View.GONE
        }

        binding.root.setOnClickListener { itemClick(item) }

        binding.btnShowMore.setOnClickListener {
            item.isExpended = true
            binding.tvPreview.maxLines = Int.MAX_VALUE
            binding.btnShowMore.visibility = View.GONE
        }

        val majorImage =
            when (item.tag) {
                "ANDROID" -> R.drawable.ic_android
                "IOS" -> R.drawable.ic_ios
                "WEB" -> R.drawable.ic_web
                "SERVER" -> R.drawable.ic_server
                "DESIGN" -> R.drawable.ic_design
                else -> R.drawable.ic_android
            }
        Glide.with(binding.ivMajor.context)
            .load(majorImage)
            .into(binding.ivMajor)
    }
}
