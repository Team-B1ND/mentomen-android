package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.User
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface MyProfileRepository {
    fun getMyInfo(): Flow<Result<User>>
}
