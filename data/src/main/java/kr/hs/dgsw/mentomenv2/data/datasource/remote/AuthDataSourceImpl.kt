package kr.hs.dgsw.mentomenv2.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.remote.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse
import kr.hs.dgsw.mentomenv2.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthService
) : AuthDataSource {
    override fun dAuthSignIn(dAuthSignInRequest: DAuthSignInRequest): Flow<DAuthSignInResponse> {
        return flow {
            emit(api.signIn(dAuthSignInRequest).data)
        }
    }
}
