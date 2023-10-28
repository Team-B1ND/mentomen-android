package kr.hs.dgsw.mentomenv2.data.datasource.remote

import kr.hs.dgsw.mentomenv2.data.remote.PostDataSource
import kr.hs.dgsw.mentomenv2.data.service.PostService
import kr.hs.dgsw.mentomenv2.domain.model.Post
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(
    private val api: PostService
): PostDataSource {
    override suspend fun getAllPost(): List<Post> {
        return api.getAllPost().execute().body()!!.data
    }

    override suspend fun getPostByTag(tag: String): List<Post> {
        return api.getPostByTag(tag).execute().body()!!.data
    }

}