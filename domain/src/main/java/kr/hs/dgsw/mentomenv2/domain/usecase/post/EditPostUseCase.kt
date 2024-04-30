package kr.hs.dgsw.mentomenv2.domain.usecase.post

import kr.hs.dgsw.mentomenv2.domain.params.PostEditParam
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import javax.inject.Inject

class EditPostUseCase
    @Inject
    constructor(
        private val postRepository: PostRepository,
    ) {
        operator fun invoke(postEditParam: PostEditParam) = postRepository.editPost(postEditParam)
    }
