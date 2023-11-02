package kr.hs.dgsw.mentomenv2.data.repository

import kr.hs.dgsw.mentomenv2.data.remote.TokenDataSource
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val remote: TokenDataSource
) : TokenRepository {
    override suspend fun getToken(): Token =
        remote.getToken()

    override suspend fun setToken(token: Token) {
        remote.setToken(token)
    }
    override suspend fun deleteToken() {
        remote.deleteToken()
    }
}
