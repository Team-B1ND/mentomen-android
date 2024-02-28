package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.datasource.PostDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class PostRepositoryImplImpl
@Inject
constructor(
    private val remote: PostDataSource,
) : BaseRepositoryImpl(), PostRepository {
    override fun getAllPost(): Flow<Result<List<Post>>> =
        execute { remote.getAllPost() }

    override fun getPostByTag(tag: String): Flow<Result<List<Post>>> =
        execute { remote.getPostByTag(tag) }

    override fun submitPost(postSubmitParam: PostSubmitParam): Flow<Result<Unit>> =
        execute { remote.submitPost(postSubmitParam) }

    override fun getPostById(id: Int): Flow<Result<Post>> =
        execute { remote.getPostById(id) }
}
