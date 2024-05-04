package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.data.response.GetCodeResponse
import kotlinx.coroutines.flow.Flow

interface DAuthDataSource {
    fun getCode(
        id: String,
        pw: String,
        clientId: String,
        redirectURL: String,
    ): Flow<GetCodeResponse>
}
