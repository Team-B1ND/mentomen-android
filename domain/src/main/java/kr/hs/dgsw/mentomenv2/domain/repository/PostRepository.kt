package kr.hs.dgsw.mentomenv2.domain.repository

import kr.hs.dgsw.mentomenv2.domain.model.Post

interface PostRepository {
    suspend fun getAllPost(): List<Post>
    suspend fun getPostByTag(tag: String): List<Post>
}