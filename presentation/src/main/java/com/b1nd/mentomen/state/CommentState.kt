package com.b1nd.mentomen.state

import com.b1nd.mentomen.domain.model.Comment

data class CommentState(
    val commentList: List<Comment>? = emptyList(),
)
