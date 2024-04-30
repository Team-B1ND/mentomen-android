package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.util.Result

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
