package kr.hs.dgsw.mentomenv2.domain.repository

import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase

interface AuthRepository {
    suspend fun signIn(dAuthParam: SignInUseCase.DAuthParam)
}