package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.data.response.NoticeResponse
import com.b1nd.mentomen.data.response.NoticeStatusResponse
import kotlinx.coroutines.flow.Flow

interface NoticeDataSource {
    fun checkNotice(): Flow<NoticeStatusResponse>

    fun getNotices(): Flow<List<NoticeResponse>>
}
