package kr.hs.dgsw.mentomenv2.domain.usecase.post

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class GetPostsByTagUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(tag: String): Flow<Result<List<Post>>> =
        postRepository.getPostByTag(tag)
}
