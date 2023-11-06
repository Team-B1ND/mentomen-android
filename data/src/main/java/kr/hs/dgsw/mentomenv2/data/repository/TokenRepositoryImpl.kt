package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.remote.TokenDataSource
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val remote: TokenDataSource
) : TokenRepository {
    override fun getToken(): Flow<Token> =
        remote.getToken()

    override fun setToken(token: Token) {
        remote.setToken(token)
    }
    override fun deleteToken() {
        remote.deleteToken()
    }
}
