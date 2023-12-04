package kr.hs.dgsw.mentomenv2.state

import kr.hs.dgsw.mentomenv2.domain.model.Post

data class PostState(
    val tag: String = "",
    val postList: List<Post>? = null,
    val error: String = "",
)
