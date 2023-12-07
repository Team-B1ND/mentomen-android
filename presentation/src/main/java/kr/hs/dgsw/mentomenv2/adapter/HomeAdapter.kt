package kr.hs.dgsw.mentomenv2.adapter

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.PostDiffUtilCallback
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post

class HomeAdapter(private val itemClick: (Post) -> Unit) :
    BaseListAdapter<Post, ItemHomeBinding>(R.layout.item_home, PostDiffUtilCallback) {

    @SuppressLint("SetTextI18n")
    override fun action(item: Post, binding: ItemHomeBinding) {
        binding.item = item

        if (!item.imgUrls.isNullOrEmpty()) {
            Glide.with(binding.ivPreview.context)
                .load(item.imgUrls?.first()).into(binding.ivPreview)
        } else {
            binding.ivPreview.visibility = View.GONE
        }
        if (item.profileUrl.isNotEmpty()) {
            Glide.with(binding.ivProfile.context)
                .load(item.profileUrl)
                .transform(CircleCrop())
                .into(binding.ivProfile)
        } else {
            Glide.with(binding.ivProfile.context)
                .load(R.drawable.ic_default_user)
                .transform(CircleCrop())
                .into(binding.ivProfile)
        }

        binding.root.setOnClickListener { itemClick(item) }

        val majorImage = when (item.tag) {
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
