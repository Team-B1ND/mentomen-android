package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.DAuthUser
import kr.hs.dgsw.mentomenv2.domain.params.DAuthParam
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase

interface AuthRepository {
    fun signIn(dAuthParam: DAuthParam): Flow<DAuthUser>
}
