package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.response.ImgUrlResponse
import okhttp3.MultipartBody

interface FileDataSource {
    fun postFile(files: List<MultipartBody.Part>): Flow<List<ImgUrlResponse>>
}
