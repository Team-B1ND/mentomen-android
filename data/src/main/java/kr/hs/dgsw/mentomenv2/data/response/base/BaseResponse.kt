package kr.hs.dgsw.mentomenv2.data.response.base

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
)
