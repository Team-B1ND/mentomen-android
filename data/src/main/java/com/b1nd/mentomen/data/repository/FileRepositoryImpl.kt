package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.data.datasource.FileDataSource
import com.b1nd.mentomen.data.mapper.toModel
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.model.ImgUrl
import com.b1nd.mentomen.domain.repository.FileRepository
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import javax.inject.Inject

class FileRepositoryImpl
    @Inject
    constructor(
        private val fileDataSource: FileDataSource,
    ) : BaseRepositoryImpl(), FileRepository {
        override fun uploadFile(files: List<MultipartBody.Part>): Flow<Result<List<ImgUrl>>> =
            execute {
                fileDataSource.postFile(files).map {
                    it.map {
                        Log.d("uploadFile: ", it.toModel().imgUrl)
                        it.toModel()
                    }
                }
            }
    }
