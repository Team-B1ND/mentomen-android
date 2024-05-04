package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.ImgUrl
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface FileRepository {
    fun uploadFile(files: List<MultipartBody.Part>): Flow<Result<List<ImgUrl>>>
}
