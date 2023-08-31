package kr.hs.dgsw.mentomenv2.domain.repository

interface AuthRepository {
    suspend fun signIn()
}