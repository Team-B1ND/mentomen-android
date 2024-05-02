package kr.hs.dgsw.mentomenv2.data.datasource

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.response.NoticeResponse
import kr.hs.dgsw.mentomenv2.data.response.NoticeStatusResponse

interface NoticeDataSource {
    fun checkNotice(): Flow<NoticeStatusResponse>

    fun getNotices(): Flow<List<NoticeResponse>>
}
