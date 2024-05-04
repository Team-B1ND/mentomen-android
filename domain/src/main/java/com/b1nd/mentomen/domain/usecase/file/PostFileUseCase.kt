package com.b1nd.mentomen.domain.usecase.file

import com.b1nd.mentomen.domain.model.ImgUrl
import com.b1nd.mentomen.domain.repository.FileRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class PostFileUseCase
    @Inject
    constructor(
        private val fileRepository: FileRepository,
    ) {
        operator fun invoke(imageFiles: List<MultipartBody.Part>): Flow<Result<List<ImgUrl>>> = fileRepository.uploadFile(imageFiles)
    }
