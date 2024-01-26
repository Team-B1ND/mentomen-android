package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.datasource.MyDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.User
import kr.hs.dgsw.mentomenv2.domain.repository.MyRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
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
