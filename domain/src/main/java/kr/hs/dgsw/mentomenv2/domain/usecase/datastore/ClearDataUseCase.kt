package kr.hs.dgsw.mentomenv2.domain.usecase.datastore

import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import javax.inject.Inject

class ClearDataUseCase
    @Inject
    constructor(
        private val dataStoreRepository: DataStoreRepository,
    ) {
        operator fun invoke() = dataStoreRepository.clearData()
    }
