package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Token

interface DataStoreDataSource {
    fun saveData(key: String, value: String): Flow<Unit>
    fun getData(key: String, defaultValue: String): Flow<String>
    fun getToken(): Flow<Token>
    fun removeData(key: String): Flow<Unit>
    fun clearData(): Flow<Unit>
}
