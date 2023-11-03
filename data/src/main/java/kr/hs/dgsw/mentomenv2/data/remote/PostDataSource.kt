package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post

interface PostDataSource {
    fun getAllPost(): Flow<List<Post>>
    suspend fun getPostByTag(tag: String): List<Post>
}
