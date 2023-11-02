package kr.hs.dgsw.mentomenv2.domain.usecase.post

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.usecase.base.NoParamUseCase
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult
import javax.inject.Inject

class GetAllPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) : NoParamUseCase<List<Post>>() {
    override operator fun invoke(): Flow<NetworkResult<List<Post>>> = execute {
        Log.d("collectPostStates: ", "$$$$$$$$$$$$$$$$$$$")
        postRepository.getAllPost()
    }
}
