package com.b1nd.mentomen.data.response

data class NoticeResponse(
    val commentContent: String,
    val createDateTime: String,
    val noticeStatus: String,
    val postId: Int,
    val senderName: String,
    val senderProfileImage: String,
)
