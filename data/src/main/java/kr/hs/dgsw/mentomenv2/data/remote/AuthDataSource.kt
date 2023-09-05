package kr.hs.dgsw.mentomenv2.data.remote

import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest

interface AuthDataSource {
    suspend fun dAuthSignIn(dAuthSignInRequest: DAuthSignInRequest)
}