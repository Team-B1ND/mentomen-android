package kr.hs.dgsw.mentomenv2.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.PostDiffUtilCallback
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post

class HomeAdapter() :
    BaseListAdapter<Post, ItemHomeBinding>(R.layout.item_home, PostDiffUtilCallback) {

    override fun action(item: Post, binding: ItemHomeBinding) {
        binding.tvName.text = item.userName
        binding.tvPreview.text = item.content
        binding.tvTime.text = item.updateDateTime
        binding.tvStudentId.text =
            item.stdInfo.grade.toString() + "학년" + item.stdInfo.room.toString() + "반" + item.stdInfo.number.toString() + "번"
        Glide.with(binding.ivPreview.context)
            .load(item.imgUrls[0]).into(binding.ivPreview)
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
