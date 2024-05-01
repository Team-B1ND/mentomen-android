package kr.hs.dgsw.mentomenv2.data.datasource

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.User

interface MyProfileDataSource {
    fun getMyInfo(): Flow<User>
}