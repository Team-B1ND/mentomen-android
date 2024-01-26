package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hs.dgsw.mentomenv2.data.datasource.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote.base.RetrofitDataSourceImpl
import kr.hs.dgsw.mentomenv2.data.request.DAuthClientRequest
import kr.hs.dgsw.mentomenv2.data.response.TokenResponse
import kr.hs.dgsw.mentomenv2.data.service.AuthService
import kr.hs.dgsw.mentomenv2.domain.exception.MenToMenException
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.usecase.token.GetTokenUseCase
import javax.inject.Inject

class AuthDataSourceImpl
    @Inject
    constructor(
        getTokenUseCase: GetTokenUseCase,
    ) : RetrofitDataSourceImpl<AuthService>(getTokenUseCase), AuthDataSource {
        override val api: AuthService
            get() = createApi(AuthService::class.java)

        override fun signIn(code: String): Flow<Token> {
            Log.d("AuthDataSourceImpl", "signIn out return flow: $code")
            return flow {
                emit(api.signIn(DAuthClientRequest(code)).data)
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
