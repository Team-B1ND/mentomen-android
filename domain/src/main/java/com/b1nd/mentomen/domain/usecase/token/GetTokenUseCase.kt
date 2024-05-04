package com.b1nd.mentomen.domain.usecase.token

import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.repository.DataStoreRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
    ) {
        operator fun invoke(): Flow<Result<Token>> = dataStoreRepository.getToken()
    }
