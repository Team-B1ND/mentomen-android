package kr.hs.dgsw.mentomenv2.data.service

import kr.hs.dgsw.mentomenv2.data.request.DAuthClientRequest
import kr.hs.dgsw.mentomenv2.data.response.base.BaseResponse
import kr.hs.dgsw.mentomenv2.domain.model.Token
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/code")
    suspend fun signIn(
        @Body code: DAuthClientRequest
    ): BaseResponse<Token>
}
