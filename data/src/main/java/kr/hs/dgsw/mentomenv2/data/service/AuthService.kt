package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    fun signIn(
        @Body DAuthSignInRequest: DAuthSignInRequest
    ): BaseResponse<DAuthSignInResponse>
}
