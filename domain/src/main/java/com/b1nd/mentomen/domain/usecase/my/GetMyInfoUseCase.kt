package com.b1nd.mentomen.domain.usecase.my

import com.b1nd.mentomen.domain.model.User
import com.b1nd.mentomen.domain.repository.MyRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyInfoUseCase
    @Inject
    constructor(
        private val myRepository: MyRepository,
    ) {
        operator fun invoke(): Flow<Result<User>> = myRepository.getMyInfo()
    }
