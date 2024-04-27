package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.request.GetCodeRequest
import kr.hs.dgsw.mentomenv2.data.response.GetCodeResponse
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DAuthService {
    @POST("/login")
    suspend fun getCode(
        @Body GetCodeRequest: GetCodeRequest
    ): Call<BaseResponse<GetCodeResponse>>
}