package kr.hs.dgsw.mentomenv2.data.request

data class CommentSubmitRequest(
    val content: String,
    val postId: Int
)
