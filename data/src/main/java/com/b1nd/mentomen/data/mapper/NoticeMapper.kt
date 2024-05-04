package com.b1nd.mentomen.data.mapper

import com.b1nd.mentomen.data.response.NoticeResponse
import com.b1nd.mentomen.data.response.NoticeStatusResponse
import com.b1nd.mentomen.domain.model.Notice
import com.b1nd.mentomen.domain.model.NoticeStatus

fun NoticeStatusResponse.toModel() =
    when (this.noticeStatus) {
        "EXIST" -> NoticeStatus.EXIST
        else -> NoticeStatus.NONE
    }

fun NoticeResponse.toModel() =
    Notice(
        commentContent = this.commentContent,
        createDateTime = this.createDateTime,
        noticeStatus = this.noticeStatus,
        postId = this.postId,
        senderName = this.senderName,
        senderProfileImage = this.senderProfileImage,
    )
