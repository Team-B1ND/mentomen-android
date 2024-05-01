package kr.hs.dgsw.mentomenv2.data.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.datasource.MyProfileDataSource
import kr.hs.dgsw.mentomenv2.data.repository.base.BaseRepositoryImpl
import kr.hs.dgsw.mentomenv2.domain.model.User
import kr.hs.dgsw.mentomenv2.domain.repository.MyProfileRepository
import kr.hs.dgsw.mentomenv2.domain.util.Result
import javax.inject.Inject

class MyProfileRepositoryImpl @Inject constructor(
    private val myProfileDataSource: MyProfileDataSource,
) : BaseRepositoryImpl(),
    MyProfileRepository {
    override fun getMyInfo(): Flow<Result<User>> =
        execute {
            myProfileDataSource.getMyInfo()
        }
}