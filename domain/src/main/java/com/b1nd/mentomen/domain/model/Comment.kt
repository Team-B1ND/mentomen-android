package com.b1nd.mentomen.domain.model

data class Comment(
    val userId: Int,
    val postId: Int,
    val commentId: String,
    val userName: String,
    val profileUrl: String,
    val stdInfo: StdInfo,
    val createDateTime: String,
    val updateDateTime: String,
    val content: String,
)
