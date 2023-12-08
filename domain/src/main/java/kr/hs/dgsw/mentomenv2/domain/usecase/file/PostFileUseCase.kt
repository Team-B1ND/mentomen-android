package kr.hs.dgsw.mentomenv2.domain.usecase.file

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.repository.FileRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import okhttp3.MultipartBody
import javax.inject.Inject

class PostFileUseCase @Inject constructor(
    private val fileRepository: FileRepository
){
    operator fun invoke(imageFiles: List<MultipartBody.Part>): Flow<Result<List<String>>> =
        fileRepository.uploadFile(imageFiles)
}
