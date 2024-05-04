package com.b1nd.mentomen.domain.model

data class Notice(
    val commentContent: String,
    val createDateTime: String,
    val noticeStatus: String,
    val postId: Int,
    val senderName: String,
    val senderProfileImage: String,
)
