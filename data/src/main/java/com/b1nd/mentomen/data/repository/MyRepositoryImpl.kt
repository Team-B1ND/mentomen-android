package com.b1nd.mentomen.data.repository

import com.b1nd.mentomen.data.datasource.MyDataSource
import com.b1nd.mentomen.data.repository.base.BaseRepositoryImpl
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.domain.model.User
import com.b1nd.mentomen.domain.repository.MyRepository
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRepositoryImpl
    @Inject
    constructor(
        private val myDataSource: MyDataSource,
    ) : BaseRepositoryImpl(), MyRepository {
        override fun getMyInfo(): Flow<Result<User>> =
            execute {
                myDataSource.getMyInfo()
            }

        override fun getMyPost(): Flow<Result<List<Post>>> =
            execute {
                myDataSource.getMyPost()
            }
    }
