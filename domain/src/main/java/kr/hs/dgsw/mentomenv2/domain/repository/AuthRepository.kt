package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult

interface AuthRepository {
    fun signIn(code: String): Flow<NetworkResult<Token>>
}