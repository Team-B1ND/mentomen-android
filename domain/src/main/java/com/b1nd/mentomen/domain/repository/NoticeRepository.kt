package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Notice
import com.b1nd.mentomen.domain.model.NoticeStatus
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun checkNotice(): Flow<Result<NoticeStatus>>

    fun getNotices(): Flow<Result<List<Notice>>>
}
