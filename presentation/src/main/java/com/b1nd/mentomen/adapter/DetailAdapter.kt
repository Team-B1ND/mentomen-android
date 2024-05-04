package com.b1nd.mentomen.adapter

import com.b1nd.mentomen.R
import com.b1nd.mentomen.adapter.callback.ImageDiffUtil
import com.b1nd.mentomen.base.BaseListAdapter
import com.b1nd.mentomen.databinding.ItemDetailImageBinding
import com.b1nd.mentomen.domain.util.Log
import com.bumptech.glide.Glide

class DetailAdapter(
    private val onClick: (String) -> Unit,
) : BaseListAdapter<String, ItemDetailImageBinding>(R.layout.item_detail_image, ImageDiffUtil) {
    override fun action(
        item: String,
        binding: ItemDetailImageBinding,
    ) {
        Glide.with(binding.root.context)
            .load(item)
            .into(binding.imgUrl)

        binding.imgUrl.setOnClickListener {
            Log.d("ImageAdapter", "ImageClicked")
            onClick(item)
        }
    }
}
