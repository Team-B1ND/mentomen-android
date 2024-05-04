package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.model.User
import kotlinx.coroutines.flow.Flow

interface MyDataSource {
    fun getMyInfo(): Flow<User>

    fun getMyPost(): Flow<List<Post>>
}
