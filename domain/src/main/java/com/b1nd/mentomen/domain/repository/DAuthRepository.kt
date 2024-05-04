package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface DAuthRepository {
    fun getCode(
        id: String,
        pw: String,
        clientId: String,
        redirectURL: String,
    ): Flow<Result<Code>>
}
