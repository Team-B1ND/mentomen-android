package kr.hs.dgsw.mentomenv2.domain.usecase.post

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class PostSubmitUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(postSubmitParam: PostSubmitParam): Flow<Result<Unit>> = postRepository.submitPost(postSubmitParam)
    }
