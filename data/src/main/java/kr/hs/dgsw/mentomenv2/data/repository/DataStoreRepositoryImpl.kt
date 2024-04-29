package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.datasource.DataStoreDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.domain.repository.DataStoreRepository
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class DataStoreRepositoryImpl
@Inject
constructor(
    private val dataStoreDataSource: DataStoreDataSource,
) : BaseRepositoryImpl(), DataStoreRepository {
    override fun saveData(
        key: String,
        value: String,
    ): Flow<Result<Unit>> =
        execute {
            dataStoreDataSource.saveData(key, value)
        }

    override fun getData(
        key: String,
        defaultValue: String,
    ): Flow<Result<String>> =
        execute {
            dataStoreDataSource.getData(key, defaultValue)
        }

    override fun saveToken(
        token: Token
    ): Flow<Result<Unit>> =
        execute {
            dataStoreDataSource.saveToken(token)
        }


    override fun getToken(): Flow<Result<Token>> =
        execute {
            dataStoreDataSource.getToken()
        }

    override fun removeData(key: String): Flow<Result<Unit>> =
        execute {
            Log.d("removeData: in RepositoryImpl", "key: $key")
            dataStoreDataSource.removeData(key)
        }

    override fun clearData(): Flow<Result<Unit>> =
        execute {
            Log.d("clearData: in RepositoryImpl", "clearData: ")
            dataStoreDataSource.clearData()
        }
}
