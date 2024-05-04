package com.b1nd.mentomen.domain.usecase.notice

import com.b1nd.mentomen.domain.model.NoticeStatus
import com.b1nd.mentomen.domain.repository.NoticeRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeStatusUseCase
    @Inject
    constructor(
        private val noticeRepository: NoticeRepository,
    ) {
        operator fun invoke(): Flow<Result<NoticeStatus>> = noticeRepository.checkNotice()
    }
