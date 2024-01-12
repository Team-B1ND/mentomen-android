package kr.hs.dgsw.mentomenv2.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Result<String>> = authRepository.getAccessToken()
}