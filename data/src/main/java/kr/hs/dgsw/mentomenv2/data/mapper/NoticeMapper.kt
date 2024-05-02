package kr.hs.dgsw.mentomenv2.data.mapper

import kr.hs.dgsw.mentomenv2.data.response.NoticeResponse
import kr.hs.dgsw.mentomenv2.data.response.NoticeStatusResponse
import kr.hs.dgsw.mentomenv2.domain.model.Notice
import kr.hs.dgsw.mentomenv2.domain.model.NoticeStatus

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
