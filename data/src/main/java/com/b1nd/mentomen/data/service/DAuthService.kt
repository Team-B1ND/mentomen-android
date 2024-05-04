package com.b1nd.mentomen.data.service

import com.b1nd.mentomen.data.request.GetCodeRequest
import com.b1nd.mentomen.data.response.GetCodeResponse
import com.b1nd.mentomen.data.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface DAuthService {
    @POST("/api/auth/login")
    suspend fun getCode(
        @Body getCodeRequest: GetCodeRequest,
    ): BaseResponse<GetCodeResponse>
}
