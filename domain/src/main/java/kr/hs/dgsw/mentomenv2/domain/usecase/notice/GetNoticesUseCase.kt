package kr.hs.dgsw.mentomenv2.domain.usecase.notice

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Notice
import kr.hs.dgsw.mentomenv2.domain.repository.NoticeRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetNoticesUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) {
    operator fun invoke(): Flow<Result<List<Notice>>> = noticeRepository.getNotices()
}
