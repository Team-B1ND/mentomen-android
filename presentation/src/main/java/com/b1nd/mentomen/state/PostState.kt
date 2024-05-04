package com.b1nd.mentomen.state

import com.b1nd.mentomen.domain.model.Post

data class PostState(
    val tag: String = "",
    val postList: List<Post>? = emptyList(),
    val error: String = "",
)
