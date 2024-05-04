package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.data.datasource.AuthDataSource
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.model.Token
import com.b1nd.mentomen.domain.repository.AuthRepository
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val authDataSource: AuthDataSource,
    ) : BaseRepositoryImpl(), AuthRepository {
        override fun signIn(code: Code): Flow<Result<Token>> {
            Log.d("AuthRepositoryImpl", "signIn: $code")
            return execute { authDataSource.signIn(code.code) }
        }

        override fun getAccessToken(): Flow<Result<String>> {
            return execute {
                authDataSource.getAccessToken().map {
                    it.toString()
                }
            }
        }
    }
