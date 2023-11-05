package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token

interface TokenDataSource {
    fun getToken(): Flow<Token>
    fun setToken(token: Token)
    fun deleteToken()
}
