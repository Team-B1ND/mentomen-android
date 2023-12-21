package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.mentomenv2.data.mapper.toModel
import kr.hs.dgsw.mentomenv2.data.remote.FileDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl
import kr.hs.dgsw.mentomenv2.domain.repository.FileRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import okhttp3.MultipartBody
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileDataSource: FileDataSource
) : BaseRepositoryImpl(), FileRepository {
    override fun uploadFile(files: List<MultipartBody.Part>): Flow<Result<List<ImgUrl>>> {
        return execute {
            fileDataSource.postFile(files).map {
                it.map {
                    it.toModel()
                }
            }
        }
    }
}
