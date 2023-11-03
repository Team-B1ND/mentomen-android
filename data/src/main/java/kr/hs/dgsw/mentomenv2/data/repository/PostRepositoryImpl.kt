package kr.hs.dgsw.mentomenv2.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.remote.PostDataSource
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val remote: PostDataSource
) : PostRepository {
    override fun getAllPost(): Flow<List<Post>> {
        return remote.getAllPost()
    }

    override suspend fun getPostByTag(tag: String): List<Post> {
        return remote.getPostByTag(tag)
    }
}
