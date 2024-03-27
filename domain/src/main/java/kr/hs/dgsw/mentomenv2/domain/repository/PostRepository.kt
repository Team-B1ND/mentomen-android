package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.params.PostEditParam
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface PostRepository {
    fun getAllPost(): Flow<Result<List<Post>>>

    fun getPostByTag(tag: String): Flow<Result<List<Post>>>

    fun submitPost(postSubmitParam: PostSubmitParam): Flow<Result<Unit>>

    fun getPostById(id: Int): Flow<Result<Post>>

    fun deletePostById(id: Int): Flow<Result<Unit>>

    fun editPost(postEditParam: PostEditParam): Flow<Result<Unit>>
}
