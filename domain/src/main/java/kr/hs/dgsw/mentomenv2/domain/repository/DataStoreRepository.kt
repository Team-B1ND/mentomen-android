package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface DataStoreRepository {
    suspend fun saveData(key: String, value: String)
    fun getData(key: String, defaultValue: String): Flow<Result<String>>
    fun getToken(): Flow<Result<Token>>
    suspend fun removeData(key: String)
    suspend fun clearData()
}
