package com.b1nd.mentomen.data.datasourceimpl.remote

import com.b1nd.mentomen.domain.exception.MenToMenException
import com.b1nd.mentomen.data.datasource.NoticeDataSource
import com.b1nd.mentomen.data.response.NoticeResponse
import com.b1nd.mentomen.data.response.NoticeStatusResponse
import com.b1nd.mentomen.data.service.NoticeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoticeDataSourceImpl
    @Inject
    constructor(
        private val noticeService: NoticeService,
    ) : NoticeDataSource {
        override fun checkNotice(): Flow<NoticeStatusResponse> =
            flow {
                val response = noticeService.checkNotice().execute()
                if (response.isSuccessful) {
                    emit(response.body()!!.data)
                } else {
                    throw MenToMenException(response.message())
                }
            }.flowOn(Dispatchers.IO)

        override fun getNotices(): Flow<List<NoticeResponse>> =
            flow {
                val response = noticeService.getNotice().execute()
                if (response.isSuccessful) {
                    emit(response.body()!!.data)
                } else {
                    throw MenToMenException(response.message())
                }
            }.flowOn(Dispatchers.IO)
    }
