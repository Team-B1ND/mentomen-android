package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.data.datasource.NoticeDataSource
import com.b1nd.mentomen.data.mapper.toModel
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.model.Notice
import com.b1nd.mentomen.domain.model.NoticeStatus
import com.b1nd.mentomen.domain.repository.NoticeRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoticeRepositoryImpl
    @Inject
    constructor(
        private val noticeDataSource: NoticeDataSource,
    ) : BaseRepositoryImpl(), NoticeRepository {
        override fun checkNotice(): Flow<Result<NoticeStatus>> =
            execute {
                noticeDataSource.checkNotice().map { it.toModel() }
            }

        override fun getNotices(): Flow<Result<List<Notice>>> =
            execute {
                noticeDataSource.getNotices().map { it.map { notice -> notice.toModel() } }
            }
    }
