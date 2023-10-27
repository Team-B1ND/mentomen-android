package kr.hs.dgsw.mentomenv2.data.remote

import kr.hs.dgsw.mentomenv2.domain.model.Token

interface TokenDataSource {
    suspend fun getToken(): Token
    suspend fun setToken(token: Token)
}
