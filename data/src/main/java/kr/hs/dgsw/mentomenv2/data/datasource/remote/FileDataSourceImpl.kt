package kr.hs.dgsw.mentomenv2.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.remote.FileDataSource
import kr.hs.dgsw.mentomenv2.data.service.FileService
import okhttp3.MultipartBody
import javax.inject.Inject

class FileDataSourceImpl @Inject constructor(
    private val api: FileService
) : FileDataSource {
    override fun postFile(files: List<MultipartBody.Part>): Flow<List<String>> {
        return flow {
            emit(api.loadImage(files).data)
        }
    }
}
