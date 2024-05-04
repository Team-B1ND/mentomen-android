package com.b1nd.mentomen.domain.usecase.post

import com.b1nd.mentomen.domain.params.PostEditParam
import com.b1nd.mentomen.domain.repository.PostRepository
import javax.inject.Inject

class EditPostUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(postEditParam: PostEditParam) = postRepository.editPost(postEditParam)
    }
