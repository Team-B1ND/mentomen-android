package kr.hs.dgsw.mentomenv2.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.repository.DAuthRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetCodeUseCase
    @Inject
    constructor(
        private val dAuthRepository: DAuthRepository,
    ) {
        operator fun invoke(
            id: String,
            pw: String,
            clientId: String,
            redirectURL: String,
        ): Flow<Result<Code>> =
            dAuthRepository
                .getCode(id, pw, clientId, redirectURL)
    }
