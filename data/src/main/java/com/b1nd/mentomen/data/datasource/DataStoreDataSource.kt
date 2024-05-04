package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {
    fun saveData(
        key: String,
        value: String,
    ): Flow<Unit>

    fun saveToken(token: Token): Flow<Unit>

    fun getData(
        key: String,
        defaultValue: String,
    ): Flow<String>

    fun getToken(): Flow<Token>

    fun removeData(key: String): Flow<Unit>

    fun clearData(): Flow<Unit>
}
