package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface DAuthRepository {
    fun getCode(
        id: String,
        pw: String,
        clientId: String,
        redirectURL: String,
    ): Flow<Result<Code>>
}
