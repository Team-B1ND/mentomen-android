package kr.hs.dgsw.mentomenv2.data.remote

import kr.hs.dgsw.mentomenv2.domain.model.Post

interface PostDataSource {
    suspend fun getAllPost(): List<Post>
    suspend fun getPostByTag(tag: String): List<Post>
}
