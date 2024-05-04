package com.b1nd.mentomen.data.service

import com.b1nd.mentomen.data.request.SingInRequest
import com.b1nd.mentomen.data.response.TokenResponse
import com.b1nd.mentomen.data.response.base.BaseResponse
import com.b1nd.mentomen.domain.model.Token
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
