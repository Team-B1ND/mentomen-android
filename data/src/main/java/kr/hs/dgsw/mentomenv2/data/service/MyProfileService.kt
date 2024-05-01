package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import kr.hs.dgsw.mentomenv2.domain.model.User
import retrofit2.Call
import retrofit2.http.GET

interface MyProfileService {
    @GET("user/my")
    fun getUserInfo(): Call<BaseResponse<User>>
}