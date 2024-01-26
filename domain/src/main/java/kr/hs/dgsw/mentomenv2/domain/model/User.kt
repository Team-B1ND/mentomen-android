package kr.hs.dgsw.mentomenv2.domain.model

data class User(
    val email: String = "",
    val name: String = "",
    val profileImage: String? = "",
    val roles: String = "STUDENT",
    val stdInfo: StdInfo = StdInfo(0, 0, 0),
    val userId: Int = 0
)
