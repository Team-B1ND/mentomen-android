package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.datasource.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Code
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Result
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
