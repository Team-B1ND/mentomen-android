package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface FileDataSource {
    fun postFile(files: List<MultipartBody.Part>): Flow<List<String>>
}