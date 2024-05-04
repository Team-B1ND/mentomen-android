package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.data.response.TokenResponse
import com.b1nd.mentomen.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun signIn(code: String): Flow<Token>

    fun getAccessToken(): Flow<TokenResponse>
}
