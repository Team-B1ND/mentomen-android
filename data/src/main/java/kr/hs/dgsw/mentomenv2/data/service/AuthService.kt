package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.b1nd.intern.mentomen.network.base.BaseResponse
import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    fun signIn(
        @Body DAuthSignInRequest: DAuthSignInRequest
    ): Call<BaseResponse<DAuthSignInResponse>>
}