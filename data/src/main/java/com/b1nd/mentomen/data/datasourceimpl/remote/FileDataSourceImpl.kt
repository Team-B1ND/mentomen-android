package com.b1nd.mentomen.data.datasourceimpl.remote

import com.b1nd.mentomen.data.datasource.FileDataSource
import com.b1nd.mentomen.data.response.ImgUrlResponse
import com.b1nd.mentomen.data.service.FileService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class FileDataSourceImpl
    @Inject
    constructor(
        private val api: FileService,
    ) : FileDataSource {
        override fun postFile(files: List<MultipartBody.Part>): Flow<List<ImgUrlResponse>> {
            return flow {
                val response = api.postFile(files).execute()

                if (response.isSuccessful) {
                    emit(response.body()?.data ?: emptyList())
                } else {
                    emit(emptyList())
                }
            }.flowOn(Dispatchers.IO)
        }
    }
