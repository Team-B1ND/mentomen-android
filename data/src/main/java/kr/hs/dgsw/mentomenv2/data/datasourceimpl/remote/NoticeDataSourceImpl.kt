package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.datasource.NoticeDataSource
import kr.hs.dgsw.mentomenv2.data.service.NoticeService
import kr.hs.dgsw.mentomenv2.domain.model.Notice
import kr.hs.dgsw.mentomenv2.domain.model.NoticeStatus
import javax.inject.Inject

class NoticeDataSourceImpl @Inject constructor(
    private val noticeService: NoticeService
): NoticeDataSource {
    override fun checkNotice(): Flow<NoticeStatus> =
        flow {
            noticeService.checkNotice().data
        }

    override fun getNotices(): Flow<List<Notice>> =
        flow {
            noticeService.getNotice().data
        }
}
