package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hs.dgsw.mentomenv2.data.datasource.MyDataSource
import kr.hs.dgsw.mentomenv2.data.service.MyService
import kr.hs.dgsw.mentomenv2.domain.exception.MenToMenException
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.StdInfo
import kr.hs.dgsw.mentomenv2.domain.model.User
import retrofit2.HttpException
import javax.inject.Inject

class MyDataSourceImpl
    @Inject
    constructor(
        private val myApi: MyService,
    ) : MyDataSource {
        override fun getMyInfo(): Flow<User> =
            flow {
                val response = myApi.getUserInfo().execute()
                if (response.isSuccessful) {
                    if (response.body()?.data?.profileImage == null) {
                        emit(
                            User(
                                email = response.body()?.data?.email ?: "",
                                name = response.body()?.data?.name ?: "",
                                profileImage = "",
                                roles = response.body()?.data?.roles ?: "",
                                stdInfo = response.body()?.data?.stdInfo ?: StdInfo(0, 0, 0),
                                userId = response.body()?.data?.userId ?: 0,
                            ),
                        )
                    } else {
                        emit(response.body()?.data ?: User())
                    }
                } else {
                    throw MenToMenException(response.message())
                }
            }.flowOn(Dispatchers.IO)

        override fun getMyPost(): Flow<List<Post>> =
            flow {
                val response = myApi.getMyPost().execute()

                if (response.isSuccessful) {
                    emit(response.body()?.data ?: emptyList())
                } else {
                    throw MenToMenException(response.message())
                }
            }.flowOn(Dispatchers.IO)
    }
