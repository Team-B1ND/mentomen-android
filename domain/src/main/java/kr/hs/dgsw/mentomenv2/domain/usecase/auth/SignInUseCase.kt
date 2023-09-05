package kr.hs.dgsw.mentomenv2.domain.usecase.auth

import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(dAuthParam: DAuthParam) = kotlin.runCatching {
        repository.signIn(dAuthParam)
    }

    data class DAuthParam(
        val id: String,
        val pw: String,
        val clientId: String,
        val redirectUrl: String,
        val state: String? = null
    )
}
