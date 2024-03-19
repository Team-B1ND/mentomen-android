package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.datasource.PostDataSource
import kr.hs.dgsw.mentomenv2.data.service.PostService
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.params.PostSubmitParam
import javax.inject.Inject

class PostDataSourceImpl
@Inject
constructor(
    private val api: PostService,
) : PostDataSource {
    override fun getAllPost(): Flow<List<Post>> {
        return flow {
            emit(api.getAllPost().data)
        }
    }

    override fun getPostByTag(tag: String): Flow<List<Post>> {
        return flow {
            emit(api.getPostByTag(tag).data)
        }
    }

    override fun submitPost(postSubmitParam: PostSubmitParam): Flow<Unit> {
        return flow {
            emit(api.submitPost(postSubmitParam).data)
        }
    }

    override fun getPostById(id: Int): Flow<Post> {
        return flow {
            emit(api.getPostById(id).data)
        }
    }

    override fun deletePostById(id: Int): Flow<Unit> {
        return flow {
            emit(api.deletePostById(id).data)
        }
    }
}
