package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(code: Code): Flow<Result<Token>>

    fun getAccessToken(): Flow<Result<String>>
}
