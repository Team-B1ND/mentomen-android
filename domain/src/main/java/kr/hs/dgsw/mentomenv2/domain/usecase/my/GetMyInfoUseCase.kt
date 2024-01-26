package kr.hs.dgsw.mentomenv2.domain.usecase.my

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.User
import kr.hs.dgsw.mentomenv2.domain.repository.MyRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetMyInfoUseCase
    @Inject
    constructor(
        private val myRepository: MyRepository,
    ) {
        operator fun invoke(): Flow<Result<User>> = myRepository.getMyInfo()
    }
