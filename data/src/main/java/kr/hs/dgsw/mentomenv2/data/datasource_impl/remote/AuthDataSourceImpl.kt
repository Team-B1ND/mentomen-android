package kr.hs.dgsw.mentomenv2.data.datasource_impl.remote

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.datasource.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.request.DAuthClientRequest
import kr.hs.dgsw.mentomenv2.data.service.AuthService
import kr.hs.dgsw.mentomenv2.domain.model.Token
import javax.inject.Inject

class AuthDataSourceImpl
    @Inject
    constructor(
        private val api: AuthService,
    ) : AuthDataSource {
        override fun signIn(code: String): Flow<Token> {
            Log.d("AuthDataSourceImpl", "signIn out return flow: $code")
            return flow {
                emit(api.signIn(DAuthClientRequest(code)).data)
            }
        }
    }