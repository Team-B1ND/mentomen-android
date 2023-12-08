package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.util.Result
import okhttp3.MultipartBody

interface FileRepository {
    fun uploadFile(files: List<MultipartBody.Part>): Flow<Result<List<String>>>
}