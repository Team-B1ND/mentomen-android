package kr.hs.dgsw.mentomenv2.util

import java.util.Calendar

fun translateDate(
    dateTime: String,
): String {
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

    return when {
        years > 0 -> "${years.toInt()}년 전"
        months > 0 -> "${months.toInt()}달 전"
        weeks > 0 -> "${weeks.toInt()}주 전"
        days > 0 -> "${days.toInt()}일 전"
        hours > 0 -> "${hours.toInt()}시간 전"
        minutes > 0 -> "${minutes.toInt()}분 전"
        seconds > 0 -> "${seconds.toInt()}초 전"
        else -> "방금 전"
    }
}