package kr.hs.dgsw.mentomenv2.data.datasource

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.response.TokenResponse
import kr.hs.dgsw.mentomenv2.domain.model.Token

interface AuthDataSource {
    fun signIn(code: String): Flow<Token>

    fun getAccessToken(): Flow<TokenResponse>
}
