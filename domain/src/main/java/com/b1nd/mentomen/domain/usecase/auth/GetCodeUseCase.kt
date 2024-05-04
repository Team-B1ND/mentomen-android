package com.b1nd.mentomen.domain.usecase.auth

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.domain.repository.DAuthRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
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
        ): Flow<Result<com.b1nd.mentomen.domain.model.Code>> =
            dAuthRepository
                .getCode(id, pw, clientId, redirectURL)
    }
