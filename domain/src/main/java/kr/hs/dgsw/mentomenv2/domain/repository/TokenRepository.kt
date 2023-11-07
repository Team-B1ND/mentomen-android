package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult

interface TokenRepository {
    fun getToken(): Flow<NetworkResult<Token>>
    fun setToken(refreshToken: Token)
    fun deleteToken()
}
