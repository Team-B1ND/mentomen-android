package kr.hs.dgsw.mentomenv2.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.ImageDiffUtil
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemDetailImageBinding
import kr.hs.dgsw.mentomenv2.domain.util.Log

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
