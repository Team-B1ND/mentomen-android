package kr.hs.dgsw.mentomenv2.domain.usecase.post

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class DeletePostByIdUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(id: Int): Flow<Result<Unit>> = postRepository.deletePostById(id)
    }
