package kr.hs.dgsw.mentomenv2.domain.repository

interface TokenRepository {
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun setAccessToken(accessToken: String)
    suspend fun setRefreshToken(refreshToken: String)
}
