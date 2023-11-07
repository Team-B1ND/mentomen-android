package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.util.NetworkResult

interface PostRepository {
    fun getAllPost(): Flow<NetworkResult<List<Post>>>
    fun getPostByTag(tag: String): Flow<NetworkResult<List<Post>>>
}
