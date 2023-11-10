package kr.hs.dgsw.mentomenv2.state

data class TokenState(
    val accessToken: String,
    val refreshToken: String,
    val isAutoLogin: Boolean
)
