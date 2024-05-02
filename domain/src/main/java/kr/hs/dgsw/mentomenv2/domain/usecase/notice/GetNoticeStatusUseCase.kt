package kr.hs.dgsw.mentomenv2.domain.usecase.notice

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.NoticeStatus
import kr.hs.dgsw.mentomenv2.domain.repository.NoticeRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetNoticeStatusUseCase
    @Inject
    constructor(
        private val noticeRepository: NoticeRepository,
    ) {
        operator fun invoke(): Flow<Result<NoticeStatus>> = noticeRepository.checkNotice()
    }
