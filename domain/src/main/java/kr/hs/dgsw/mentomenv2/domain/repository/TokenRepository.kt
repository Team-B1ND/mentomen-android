package kr.hs.dgsw.mentomenv2.domain.repository

import kr.hs.dgsw.mentomenv2.domain.model.Token

interface TokenRepository {
    suspend fun getToken(): Token
    suspend fun setToken(refreshToken: Token)
}
