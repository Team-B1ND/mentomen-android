package kr.hs.dgsw.mentomenv2.state

import kr.hs.dgsw.mentomenv2.domain.model.Comment

data class CommentState(
    val commentList: List<Comment>? = emptyList(),
    val error: String = "",
)
