package kr.hs.dgsw.mentomenv2.data.repository

import kr.hs.dgsw.mentomenv2.data.remote.TokenDataSource
import kr.hs.dgsw.mentomenv2.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val remote: TokenDataSource
) : TokenRepository {
    override suspend fun getAccessToken(): String =
        remote.getAccessToken()

    override suspend fun getRefreshToken(): String =
        remote.getRefreshToken()

    override suspend fun setAccessToken(accessToken: String) {
        remote.setAccessToken(accessToken)
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        remote.setRefreshToken(refreshToken)
    }
}
