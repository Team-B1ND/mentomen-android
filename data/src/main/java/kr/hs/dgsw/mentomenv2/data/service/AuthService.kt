package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.request.SingInRequest
import kr.hs.dgsw.mentomenv2.data.response.TokenResponse
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import kr.hs.dgsw.mentomenv2.domain.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("auth/code")
    suspend fun signIn(
        @Body code: SingInRequest,
    ): BaseResponse<Token>

    @GET("auth/refreshToken")
    fun refreshToken(): Call<BaseResponse<TokenResponse>>
}
