package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.data.datasource.DAuthDataSource
import com.b1nd.mentomen.data.mapper.toModel
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.repository.DAuthRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DAuthRepositoryImpl
    @Inject
    constructor(
        private val dAuthDataSource: DAuthDataSource,
    ) : BaseRepositoryImpl(), DAuthRepository {
        override fun getCode(
            id: String,
            pw: String,
            clientId: String,
            redirectURL: String,
        ): Flow<Result<Code>> =
            execute {
                dAuthDataSource.getCode(id, pw, clientId, redirectURL).map { it.toModel() }
            }
    }
