package kr.hs.dgsw.mentomenv2.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.datasource.AuthDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val remote: AuthDataSource,
    ) : BaseRepositoryImpl(), AuthRepository {
        override fun signIn(code: String): Flow<Result<Token>> {
            Log.d("AuthRepositoryImpl", "signIn: $code")
            return execute { remote.signIn(code) }
        }
    }
