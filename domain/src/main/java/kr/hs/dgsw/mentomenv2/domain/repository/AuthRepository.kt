package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface AuthRepository {
    fun signIn(code: Code): Flow<Result<Token>>

    fun getAccessToken(): Flow<Result<String>>
}
