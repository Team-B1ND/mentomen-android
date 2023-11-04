package kr.hs.dgsw.mentomenv2.data.remote

import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse

interface AuthDataSource {
    suspend fun dAuthSignIn(dAuthSignInRequest: DAuthSignInRequest): DAuthSignInResponse
}
