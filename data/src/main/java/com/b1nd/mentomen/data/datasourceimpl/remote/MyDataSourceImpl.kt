package com.b1nd.mentomen.data.datasourceimpl.remote

import com.b1nd.mentomen.domain.exception.MenToMenException
import com.b1nd.mentomen.data.datasource.MyDataSource
import com.b1nd.mentomen.data.service.MyService
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.model.StdInfo
import com.b1nd.mentomen.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyDataSourceImpl
    @Inject
    constructor(
        private val myService: MyService,
    ) : MyDataSource {
        override fun getMyInfo(): Flow<User> =
            flow {
                val response = myService.getUserInfo().execute()
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
                val response = myService.getMyPost().execute()

                if (response.isSuccessful) {
                    emit(response.body()?.data ?: emptyList())
                } else {
                    throw MenToMenException(response.message())
                }
            }.flowOn(Dispatchers.IO)
    }
