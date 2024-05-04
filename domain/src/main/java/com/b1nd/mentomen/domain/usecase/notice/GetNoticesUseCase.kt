package com.b1nd.mentomen.domain.usecase.notice

import com.b1nd.mentomen.domain.model.Notice
import com.b1nd.mentomen.domain.repository.NoticeRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticesUseCase
    @Inject
    constructor(
        private val noticeRepository: NoticeRepository,
    ) {
        operator fun invoke(): Flow<Result<List<Notice>>> = noticeRepository.getNotices()
    }
