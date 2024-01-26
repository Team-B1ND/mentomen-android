package kr.hs.dgsw.mentomenv2.data.request

data class CommentUpdateRequest(
    val commentId: Int,
    val content: String,
)
