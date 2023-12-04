package kr.hs.dgsw.mentomenv2.domain.model

data class DAuthUser(
    val email: String,
    val grade: String,
    val name: String,
    val number: String,
    val profileImage: String,
    val role: String,
    val room: String,
    val uniqueId: String
)