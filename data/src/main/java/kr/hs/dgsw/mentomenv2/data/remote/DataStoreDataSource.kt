package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token

interface DataStoreDataSource {
    suspend fun saveData(key: String, value: String)
    fun getData(key: String, defaultValue: String): Flow<String>
    fun getToken(): Flow<Token>
    suspend fun removeData(key: String)
    suspend fun clearData()
}
