package com.b1nd.mentomen.adapter

import android.view.View
import com.b1nd.mentomen.R
import com.b1nd.mentomen.adapter.callback.PostDiffUtil
import com.b1nd.mentomen.base.BaseListAdapter
import com.b1nd.mentomen.databinding.ItemHomeBinding
import com.b1nd.mentomen.domain.model.Post
import com.bumptech.glide.Glide

class HomeAdapter(
    private val itemClick: (Post) -> Unit,
) :
    BaseListAdapter<Post, ItemHomeBinding>(R.layout.item_home, PostDiffUtil) {
    override fun action(
        item: Post,
        binding: ItemHomeBinding,
    ) {
        val isDummy =
            !item.isExpended && item.content.isEmpty() && item.userName.isEmpty() && item.author == 0 && item.tag.isEmpty()
        binding.item = item
        if (!isDummy) {
            if (item.isExpended) {
                binding.tvPreview.maxLines = Int.MAX_VALUE
                binding.btnShowMore.visibility = View.GONE
            } else {
                binding.tvPreview.maxLines = 3
                binding.tvPreview.post {
                    val showMore = item.content.count { it == '\n' } + 1 >= 3
                    if (showMore) {
                        item.isExpended = true
                        binding.btnShowMore.visibility = View.VISIBLE
                    } else {
                        item.isExpended = false
                        binding.btnShowMore.visibility = View.GONE
                    }
                }
            }

            if (!item.imgUrls.isNullOrEmpty()) {
                binding.ivPreview.visibility = View.VISIBLE
                binding.cvPreview.visibility = View.VISIBLE
                Glide.with(binding.ivPreview.context)
                    .load(item.imgUrls?.first())
                    .into(binding.ivPreview)
            } else {
                binding.cvPreview.visibility = View.GONE
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
        } else {
            binding.clComment.visibility = View.INVISIBLE
        }
    }
}
