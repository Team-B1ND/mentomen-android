package com.b1nd.mentomen.data.request

data class CommentSubmitRequest(
    val content: String,
    val postId: Int,
)
