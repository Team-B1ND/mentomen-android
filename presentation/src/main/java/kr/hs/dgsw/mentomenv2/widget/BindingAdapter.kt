package kr.hs.dgsw.mentomenv2.widget

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.util.getYearTimeDate
import java.util.Calendar

@BindingAdapter("designButtonState")
fun setDesignButtonState(
    view: Button,
    tagState: String,
) {
    if (tagState == "DESIGN" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_design)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("webButtonState")
fun setWebButtonState(
    view: Button,
    tagState: String,
) {
    if (tagState == "WEB" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_web)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("androidButtonState")
fun setAndroidButtonState(
    view: Button,
    tagState: String,
) {
    if (tagState == "ANDROID" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_android)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("serverButtonState")
fun setServerButtonState(
    view: Button,
    tagState: String,
) {
    if (tagState == "SERVER" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_server)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("iosButtonState")
fun setIosButtonState(
    view: Button,
    tagState: String,
) {
    if (tagState == "IOS" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_ios)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("image")
fun loadImage(
    view: ImageView,
    imageUrl: String?,
) {
    if (imageUrl.isNullOrBlank().not()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("circleImage")
fun loadCircleImage(
    view: ImageView,
    imageUrl: String?,
) {
    if (imageUrl.isNullOrBlank().not()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transform(CircleCrop())
            .into(view)
    }
}

@BindingAdapter("notice")
fun comment(
    view: TextView,
    comment: String,
) {
    view.text = "\"$comment\""
}

@BindingAdapter("date")
fun translateDate(
    tvTime: TextView,
    dateTime: String,
) {
    val currentTime = Calendar.getInstance()
    currentTime.add(Calendar.HOUR_OF_DAY, -9) // 한국이 UTC+9 라서
    val postDate = dateTime.getYearTimeDate()
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
        years > 0 -> tvTime.text = "${years.toInt()}년 전"
        months > 0 -> tvTime.text = "${months.toInt()}달 전"
        weeks > 0 -> tvTime.text = "${weeks.toInt()}주 전"
        days > 0 -> tvTime.text = "${days.toInt()}일 전"
        hours > 0 -> tvTime.text = "${hours.toInt()}시간 전"
        minutes > 0 -> tvTime.text = "${minutes.toInt()}분 전"
        seconds > 0 -> tvTime.text = "${seconds.toInt()}초 전"
        else -> tvTime.text = "방금 전"
    }
}

@BindingAdapter("grade")
fun setGrade(
    view: TextView,
    grade: Int,
) {
    view.text = "${grade}학년 "
}

@BindingAdapter("room")
fun setRoom(
    view: TextView,
    room: Int,
) {
    view.text = "${room}반 "
}

@BindingAdapter("number")
fun setNumber(
    view: TextView,
    number: Int,
) {
    view.text = "${number}번"
}
