package kr.hs.dgsw.mentomenv2.data.response

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T,
)
