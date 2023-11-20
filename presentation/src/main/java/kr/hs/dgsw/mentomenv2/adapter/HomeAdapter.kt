package kr.hs.dgsw.mentomenv2.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.callback.PostDiffUtilCallback
import kr.hs.dgsw.mentomenv2.base.BaseListAdapter
import kr.hs.dgsw.mentomenv2.databinding.ItemHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.util.getYearTimeDate
import java.util.Calendar

class HomeAdapter(private val itemClick: (Post) -> Unit) :
    BaseListAdapter<Post, ItemHomeBinding>(R.layout.item_home, PostDiffUtilCallback) {

    @SuppressLint("SetTextI18n")
    override fun action(item: Post, binding: ItemHomeBinding) {
        binding.tvName.text = item.userName
        binding.tvPreview.text = item.content

        val currentTime = Calendar.getInstance()
        val postDate = item.updateDateTime.getYearTimeDate()
        val calendar = Calendar.getInstance()
        calendar.time = postDate

        val difference = currentTime.timeInMillis - calendar.timeInMillis
        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = weeks / 4
        val years = months / 12

        when {
            years > 0 -> binding.tvTime.text = "${years.toInt()}년 전"
            months > 0 -> binding.tvTime.text = "${months.toInt()}달 전"
            weeks > 0 -> binding.tvTime.text = "${weeks.toInt()}주 전"
            days > 0 -> binding.tvTime.text = "${days.toInt()}일 전"
            hours > 0 -> binding.tvTime.text = "${hours.toInt()}시간 전"
            minutes > 0 -> binding.tvTime.text = "${minutes.toInt()}분 전"
            seconds > 0 -> binding.tvTime.text = "${seconds.toInt()}초 전"
            else -> binding.tvTime.text = "방금 전"
        }

        binding.tvStudentId.text =
            item.stdInfo.grade.toString() + "학년" + item.stdInfo.room.toString() + "반" + item.stdInfo.number.toString() + "번"

        Glide.with(binding.ivPreview.context)
            .load(item.imgUrls[0]).into(binding.ivPreview)

        Glide.with(binding.ivProfile.context)
            .load(item.profileUrl)
            .transform(CircleCrop())
            .into(binding.ivProfile)

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
