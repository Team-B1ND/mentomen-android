package kr.hs.dgsw.mentomenv2.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.remote.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.request.DAuthClientRequest
import kr.hs.dgsw.mentomenv2.data.service.AuthService
import kr.hs.dgsw.mentomenv2.domain.model.Token
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthService
) : AuthDataSource {
    override fun signIn(code: String): Flow<Token> {
        return flow {
            emit(api.signIn(DAuthClientRequest(code)).data)
        }
    }
}
