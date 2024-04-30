package kr.hs.dgsw.mentomenv2.data.request

data class GetCodeRequest(
    val id: String,
    val pw: String,
    val clientId: String,
    val redirectUrl: String,
    val state: String? = null,
)
