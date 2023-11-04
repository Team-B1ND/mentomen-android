package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse

interface AuthDataSource {
    fun dAuthSignIn(dAuthSignInRequest: DAuthSignInRequest): Flow<DAuthSignInResponse>
}
