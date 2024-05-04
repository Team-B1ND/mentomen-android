package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.data.datasource.DataStoreDataSource
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.repository.DataStoreRepository
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl
    @Inject
    constructor(
        private val dataStoreDataSource: DataStoreDataSource,
    ) : BaseRepositoryImpl(), DataStoreRepository {
        override fun saveData(
            key: String,
            value: String,
        ): Flow<Result<Unit>> =
            execute {
                dataStoreDataSource.saveData(key, value)
            }

        override fun getData(
            key: String,
            defaultValue: String,
        ): Flow<Result<String>> =
            execute {
                dataStoreDataSource.getData(key, defaultValue)
            }

        override fun saveToken(token: Token): Flow<Result<Unit>> =
            execute {
                dataStoreDataSource.saveToken(token)
            }

        override fun getToken(): Flow<Result<Token>> =
            execute {
                dataStoreDataSource.getToken()
            }

        override fun removeData(key: String): Flow<Result<Unit>> =
            execute {
                Log.d("removeData: in RepositoryImpl", "key: $key")
                dataStoreDataSource.removeData(key)
            }

        override fun clearData(): Flow<Result<Unit>> =
            execute {
                Log.d("clearData: in RepositoryImpl", "clearData: ")
                dataStoreDataSource.clearData()
            }
    }
