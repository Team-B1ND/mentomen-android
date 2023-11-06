package kr.hs.dgsw.mentomenv2.domain.params

data class DAuthParam(
    val id: String,
    val pw: String,
    val clientId: String,
    val redirectUrl: String,
    val state: String? = null
)
