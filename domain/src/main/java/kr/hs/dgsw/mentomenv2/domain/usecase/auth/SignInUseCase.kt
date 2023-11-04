package kr.hs.dgsw.mentomenv2.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.DAuthUser
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.base.UseCase
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<SignInUseCase.DAuthParam, DAuthUser>() {
    override operator fun invoke(dAuthParam: DAuthParam): Flow<NetworkResult<DAuthUser>> = execute {
        authRepository.signIn(dAuthParam)
    }

    data class DAuthParam(
        val id: String,
        val pw: String,
        val clientId: String,
        val redirectUrl: String,
        val state: String? = null
    )
}
