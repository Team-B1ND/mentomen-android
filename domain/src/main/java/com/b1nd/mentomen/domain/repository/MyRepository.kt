package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.model.User
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface MyRepository {
    fun getMyInfo(): Flow<Result<User>>

    fun getMyPost(): Flow<Result<List<Post>>>
}
