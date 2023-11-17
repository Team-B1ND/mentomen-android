package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface PostRepository {
    fun getAllPost(): Flow<Result<List<Post>>>
    fun getPostByTag(tag: String): Flow<Result<List<Post>>>
}
