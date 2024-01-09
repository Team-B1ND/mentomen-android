package kr.hs.dgsw.mentomenv2.domain.model

data class User(
    val email: String,
    val name: String,
    val profileImage: String,
    val roles: String,
    val stdInfo: StdInfo,
    val userId: Int
)
