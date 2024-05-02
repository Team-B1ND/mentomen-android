package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.response.NoticeResponse
import kr.hs.dgsw.mentomenv2.data.response.NoticeStatusResponse
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface NoticeService {
    @GET("notice/check")
    fun checkNotice(): Call<BaseResponse<NoticeStatusResponse>>

    @GET("notice/list")
    fun getNotice(): Call<BaseResponse<List<NoticeResponse>>>
}
