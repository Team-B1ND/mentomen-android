package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token

interface TokenRepository {
    fun getToken(): Flow<Token>
    fun setToken(refreshToken: Token)
    fun deleteToken()
}
