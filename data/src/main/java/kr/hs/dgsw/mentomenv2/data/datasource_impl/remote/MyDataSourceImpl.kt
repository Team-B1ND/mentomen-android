package kr.hs.dgsw.mentomenv2.data.datasource_impl.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hs.dgsw.mentomenv2.data.datasource.MyDataSource
import kr.hs.dgsw.mentomenv2.data.service.MyService
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.User
import javax.inject.Inject

class MyDataSourceImpl @Inject constructor(
    private val myApi: MyService
) : MyDataSource {
    override fun getMyInfo(): Flow<User> =
        flow {
            val response = myApi.getUserInfo().execute()

            if (response.isSuccessful) {
                emit(response.body()?.data ?: User())
            } else {
                emit(User())
            }
        }.flowOn(Dispatchers.IO)

    override fun getMyPost(): Flow<List<Post>> =
        flow {
            val response = myApi.getMyPost().execute()

            if (response.isSuccessful) {
                emit(response.body()?.data ?: emptyList())
            } else {
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
}