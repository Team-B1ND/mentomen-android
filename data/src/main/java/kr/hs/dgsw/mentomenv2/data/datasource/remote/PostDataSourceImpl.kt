package kr.hs.dgsw.mentomenv2.data.datasource.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.mentomenv2.data.remote.PostDataSource
import kr.hs.dgsw.mentomenv2.data.service.PostService
import kr.hs.dgsw.mentomenv2.domain.model.Post
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val api: PostService
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
}
