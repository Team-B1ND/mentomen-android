package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.params.PostEditParam
import com.b1nd.mentomen.domain.params.PostSubmitParam
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getAllPost(): Flow<Result<List<Post>>>

    fun getPostByTag(tag: String): Flow<Result<List<Post>>>

    fun submitPost(postSubmitParam: PostSubmitParam): Flow<Result<Unit>>

    fun getPostById(id: Int): Flow<Result<Post>>

    fun deletePostById(id: Int): Flow<Result<Unit>>

    fun editPost(postEditParam: PostEditParam): Flow<Result<Unit>>
}
