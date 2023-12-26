package kr.hs.dgsw.mentomenv2.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
)
