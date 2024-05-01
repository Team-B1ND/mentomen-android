package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.datasource.DAuthDataSource
import kr.hs.dgsw.mentomenv2.data.mapper.toModel
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.repository.DAuthRepository
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Result
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
