package kr.hs.dgsw.mentomenv2.domain.usecase.auth

import kr.hs.dgsw.mentomenv2.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(createPostParam: CreatePostParam) = kotlin.runCatching {
        repository.createPost(createPostParam)
    }
}