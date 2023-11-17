package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.remote.DataStoreDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class DataSourceRepositoryImpl @Inject constructor(
    private val remote: DataStoreDataSource
) : BaseRepositoryImpl(), DataStoreRepository {
    override suspend fun saveData(key: String, value: String) {
        remote.saveData(key, value)
    }

    override fun getData(key: String, defaultValue: String): Flow<Result<String>> = execute {
        remote.getData(key, defaultValue)
    }

    override fun getToken(): Flow<Result<Token>> = execute {
        remote.getToken()
    }

    override suspend fun removeData(key: String) {
        remote.removeData(key)
    }

    override suspend fun clearData() {
        remote.clearData()
    }
}
