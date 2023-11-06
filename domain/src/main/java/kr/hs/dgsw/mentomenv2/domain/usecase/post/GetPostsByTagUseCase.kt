package kr.hs.dgsw.mentomenv2.domain.usecase.post

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.base.UseCase
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import javax.inject.Inject

class GetPostsByTagUseCase @Inject constructor(
    private val postRepository: PostRepository
) : UseCase<String, List<Post>>() {
    override operator fun invoke(tag: String): Flow<NetworkResult<List<Post>>> = execute {
        postRepository.getPostByTag(tag)
    }
}
