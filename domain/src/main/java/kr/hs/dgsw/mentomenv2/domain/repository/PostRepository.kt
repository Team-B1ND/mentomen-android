package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post

interface PostRepository {
    fun getAllPost(): Flow<List<Post>>
    suspend fun getPostByTag(tag: String): List<Post>
}
