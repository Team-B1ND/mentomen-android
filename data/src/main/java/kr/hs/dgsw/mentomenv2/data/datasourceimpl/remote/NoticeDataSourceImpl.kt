package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hs.dgsw.mentomenv2.data.datasource.NoticeDataSource
import kr.hs.dgsw.mentomenv2.data.mapper.toModel
import kr.hs.dgsw.mentomenv2.data.response.NoticeResponse
import kr.hs.dgsw.mentomenv2.data.response.NoticeStatusResponse
import kr.hs.dgsw.mentomenv2.data.service.NoticeService
import kr.hs.dgsw.mentomenv2.domain.exception.MenToMenException
import kr.hs.dgsw.mentomenv2.domain.util.Log
import javax.inject.Inject

class NoticeDataSourceImpl @Inject constructor(
    private val noticeService: NoticeService
): NoticeDataSource {
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
