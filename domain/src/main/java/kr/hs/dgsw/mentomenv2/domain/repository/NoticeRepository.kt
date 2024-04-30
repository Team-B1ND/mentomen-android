package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Notice
import kr.hs.dgsw.mentomenv2.domain.model.NoticeStatus
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface NoticeRepository {
    fun checkNotice(): Flow<Result<NoticeStatus>>
    fun getNotices(): Flow<Result<List<Notice>>>
}