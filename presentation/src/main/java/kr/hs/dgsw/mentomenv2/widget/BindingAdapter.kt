package kr.hs.dgsw.mentomenv2.widget

import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kr.hs.dgsw.mentomenv2.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

@BindingAdapter("designButtonState")
fun setDesignButtonState(view: Button, tagState: String) {
    if (tagState == "DESIGN" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_design)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("webButtonState")
fun setWebButtonState(view: Button, tagState: String) {
    if (tagState == "WEB" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_web)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("androidButtonState")
fun setAndroidButtonState(view: Button, tagState: String) {
    if (tagState == "ANDROID" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_android)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("serverButtonState")
fun setServerButtonState(view: Button, tagState: String) {
    if (tagState == "SERVER" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_server)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("iosButtonState")
fun setIosButtonState(view: Button, tagState: String) {
    if (tagState == "IOS" || tagState == "ALL") {
        view.setBackgroundResource(R.drawable.bg_selected_ios)
    } else {
        view.setBackgroundResource(R.drawable.bg_unselected_tag)
    }
}

@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrBlank().not()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("circleImage")
fun loadCircleImage(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrBlank().not()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transform(CircleCrop())
            .into(view)
    }
}

@BindingAdapter("notice")
fun comment(view: TextView, comment: String) {
    view.text = "\"$comment\""
}

@BindingAdapter("date")
fun translateDate(view: TextView, dateTime: String?) {
    if (dateTime != null) {
        val now = LocalDateTime.now()
        val time = dateTime.split(".")[0]
        val convertTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val compareSecondTime = ChronoUnit.SECONDS.between(convertTime, now)
        val compareMinuteTime = ChronoUnit.MINUTES.between(convertTime, now)
        val compareHourTime = ChronoUnit.HOURS.between(convertTime, now)
        val compareDayTime = ChronoUnit.DAYS.between(convertTime, now)
        val compareMonthTime = ChronoUnit.MONTHS.between(convertTime, now)
        when {
            compareSecondTime < 60 -> view.text = "${compareSecondTime}초전"
            compareMinuteTime < 60 -> view.text = "${compareMinuteTime}분전"
            compareHourTime < 24 -> view.text = "${compareHourTime}시간전"
            compareDayTime < when (now.monthValue) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                2 -> 28
                else -> 30
            } -> view.text = "${compareDayTime}일전"

            else -> view.text = "${compareMonthTime}달전"
        }
    } else {
        // dateTime이 null일 경우 처리 (예: 기본 텍스트 설정)
        view.text = "날짜 없음"
    }
}

@BindingAdapter("currentDate")
fun setDate(view: TextView, dateTime: String?) {
    dateTime?.let {
        val time = it.split(".")[0]
        val convertTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        view.text =
            "${convertTime.year}.${convertTime.monthValue}.${convertTime.dayOfMonth} ${if (convertTime.hour >= 12) "오후" else "오전"} ${if (convertTime.hour >= 12) convertTime.hour - 12 else convertTime.hour}:${convertTime.minute}"
    }
}

@BindingAdapter("grade")
fun setGrade(view: TextView, grade: Int) {
    view.text = "${grade}학년 "
}

@BindingAdapter("room")
fun setRoom(view: TextView, room: Int) {
    view.text = "${room}반 "
}

@BindingAdapter("number")
fun setNumber(view: TextView, number: Int) {
    view.text = "${number}번"
}
