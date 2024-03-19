package kr.hs.dgsw.mentomenv2.data.datasource

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam

interface PostDataSource {
    fun getAllPost(): Flow<List<Post>>

    fun getPostByTag(tag: String): Flow<List<Post>>

    fun submitPost(postSubmitParam: PostSubmitParam): Flow<Unit>

    fun getPostById(id: Int): Flow<Post>

    fun deletePostById(id: Int): Flow<Unit>
}
