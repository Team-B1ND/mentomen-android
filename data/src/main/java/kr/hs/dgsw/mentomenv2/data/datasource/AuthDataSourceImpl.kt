package kr.hs.dgsw.mentomenv2.data.datasource

import kr.hs.dgsw.mentomenv2.data.remote.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse
import kr.hs.dgsw.mentomenv2.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthService
) : AuthDataSource {
    override suspend fun dAuthSignIn(dAuthSignInRequest: DAuthSignInRequest): DAuthSignInResponse {
        return api.signIn(dAuthSignInRequest).execute().body()!!.data
    }
}
