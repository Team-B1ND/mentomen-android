package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.data.response.ImgUrlResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface FileDataSource {
    fun postFile(files: List<MultipartBody.Part>): Flow<List<ImgUrlResponse>>
}
