package com.b1nd.mentomen.domain.usecase.post

import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.repository.PostRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostByIdUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(id: Int): Flow<Result<Post>> = postRepository.getPostById(id)
    }
