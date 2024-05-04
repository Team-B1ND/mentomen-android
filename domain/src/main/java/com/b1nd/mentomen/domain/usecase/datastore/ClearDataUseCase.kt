package com.b1nd.mentomen.domain.usecase.datastore

import com.b1nd.mentomen.domain.repository.DataStoreRepository
import javax.inject.Inject

class ClearDataUseCase
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
    ) {
        operator fun invoke() = dataStoreRepository.clearData()
    }
