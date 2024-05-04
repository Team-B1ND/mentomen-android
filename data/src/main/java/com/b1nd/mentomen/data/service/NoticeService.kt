package com.b1nd.mentomen.data.service

import com.b1nd.mentomen.data.response.NoticeResponse
import com.b1nd.mentomen.data.response.NoticeStatusResponse
import com.b1nd.mentomen.data.response.base.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface NoticeService {
    @GET("notice/check")
    fun checkNotice(): Call<BaseResponse<NoticeStatusResponse>>

    @GET("notice/list")
    fun getNotice(): Call<BaseResponse<List<NoticeResponse>>>
}
