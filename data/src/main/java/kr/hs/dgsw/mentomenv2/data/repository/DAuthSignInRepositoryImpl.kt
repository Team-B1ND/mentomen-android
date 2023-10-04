package kr.hs.dgsw.mentomenv2.data.repository

import kr.hs.dgsw.mentomenv2.data.mapper.toModel
import kr.hs.dgsw.mentomenv2.data.mapper.toRequest
import kr.hs.dgsw.mentomenv2.data.remote.AuthDataSource
import kr.hs.dgsw.mentomenv2.domain.model.DAuthUser
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase
import javax.inject.Inject

class DAuthSignInRepositoryImpl @Inject constructor(
    private val remote: AuthDataSource
): AuthRepository{
    override suspend fun signIn(dAuthParam: SignInUseCase.DAuthParam): DAuthUser = remote.dAuthSignIn(dAuthParam.toRequest()).toModel()
}