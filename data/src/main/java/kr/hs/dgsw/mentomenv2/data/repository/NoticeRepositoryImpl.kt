package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.datasource.NoticeDataSource
import kr.hs.dgsw.mentomenv2.data.mapper.toModel
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Notice
import kr.hs.dgsw.mentomenv2.domain.model.NoticeStatus
import kr.hs.dgsw.mentomenv2.domain.repository.NoticeRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource: NoticeDataSource
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