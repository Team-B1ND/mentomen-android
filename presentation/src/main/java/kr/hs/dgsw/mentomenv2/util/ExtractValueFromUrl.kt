package kr.hs.dgsw.mentomenv2.util

fun extractValueFromUrl(
        url: String,
        paramName: String,
    ): String? {
        val regex = "$paramName=([^&]+)".toRegex()
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value
    }