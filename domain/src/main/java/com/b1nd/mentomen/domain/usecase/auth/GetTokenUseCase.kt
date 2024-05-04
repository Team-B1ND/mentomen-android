package com.b1nd.mentomen.domain.usecase.auth

import com.b1nd.mentomen.domain.repository.AuthRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        operator fun invoke(): Flow<Result<String>> = authRepository.getAccessToken()
    }
