package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun saveData(
        key: String,
        value: String,
    ): Flow<Result<Unit>>

    fun getData(
        key: String,
        defaultValue: String,
    ): Flow<Result<String>>

    fun saveToken(token: Token): Flow<Result<Unit>>

    fun getToken(): Flow<Result<Token>>

    fun removeData(key: String): Flow<Result<Unit>>

    fun clearData(): Flow<Result<Unit>>
}
