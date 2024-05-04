package com.b1nd.mentomen.data.datasourceimpl.remote

import com.b1nd.mentomen.domain.exception.MenToMenException
import com.b1nd.mentomen.data.datasource.AuthDataSource
import com.b1nd.mentomen.data.datasourceimpl.remote.base.RetrofitDataSourceImpl
import com.b1nd.mentomen.data.request.SingInRequest
import com.b1nd.mentomen.data.response.TokenResponse
import com.b1nd.mentomen.data.service.AuthService
import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.usecase.token.GetTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataSourceImpl
    @Inject
    constructor(
        getTokenUseCase: GetTokenUseCase,
    ) : RetrofitDataSourceImpl<AuthService>(getTokenUseCase), AuthDataSource {
        override val api: AuthService
            get() = createApi(AuthService::class.java)

        override fun signIn(code: String): Flow<Token> {
            return flow {
                emit(api.signIn(SingInRequest(code)).data)
            }
        }

        override fun getAccessToken(): Flow<TokenResponse> {
            return flow {
                val response = api.refreshToken().execute()

                if (response.isSuccessful) {
                    emit(response.body()?.data ?: TokenResponse())
                } else {
                    throw MenToMenException("토큰을 갱신하는데 실패했습니다.")
                }
            }.flowOn(Dispatchers.IO)
        }
    }
