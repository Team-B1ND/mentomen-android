package com.b1nd.mentomen.domain.usecase.post

import com.b1nd.mentomen.domain.repository.PostRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeletePostByIdUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(id: Int): Flow<Result<Unit>> = postRepository.deletePostById(id)
    }
