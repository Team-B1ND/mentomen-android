package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.params.PostEditParam
import com.b1nd.mentomen.domain.params.PostSubmitParam
import kotlinx.coroutines.flow.Flow

interface PostDataSource {
    fun getAllPost(): Flow<List<Post>>

    fun getPostByTag(tag: String): Flow<List<Post>>

    fun submitPost(postSubmitParam: PostSubmitParam): Flow<Unit>

    fun getPostById(id: Int): Flow<Post>

    fun deletePostById(id: Int): Flow<Unit>

    fun editPost(postEditParam: PostEditParam): Flow<Unit>
}
