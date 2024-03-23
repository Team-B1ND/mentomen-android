package kr.hs.dgsw.mentomenv2.domain.usecase.post

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetPostByIdUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(id: Int): Flow<Result<Post>> = postRepository.getPostById(id)
    }
