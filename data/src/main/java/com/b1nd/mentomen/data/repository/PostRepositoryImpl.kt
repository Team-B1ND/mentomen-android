package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.data.datasource.PostDataSource
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.params.PostEditParam
import com.b1nd.mentomen.domain.params.PostSubmitParam
import com.b1nd.mentomen.domain.repository.PostRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl
    @Inject
    constructor(
        private val remote: PostDataSource,
    ) : BaseRepositoryImpl(), PostRepository {
        override fun getAllPost(): Flow<Result<List<Post>>> = execute { remote.getAllPost() }

        override fun getPostByTag(tag: String): Flow<Result<List<Post>>> = execute { remote.getPostByTag(tag) }

        override fun submitPost(postSubmitParam: PostSubmitParam): Flow<Result<Unit>> = execute { remote.submitPost(postSubmitParam) }

        override fun getPostById(id: Int): Flow<Result<Post>> = execute { remote.getPostById(id) }

        override fun deletePostById(id: Int): Flow<Result<Unit>> = execute { remote.deletePostById(id) }

        override fun editPost(postEditParam: PostEditParam): Flow<Result<Unit>> = execute { remote.editPost(postEditParam) }
    }
