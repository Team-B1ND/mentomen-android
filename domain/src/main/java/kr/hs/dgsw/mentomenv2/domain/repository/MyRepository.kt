package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.User
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface MyRepository {
    fun getMyInfo(): Flow<Result<User>>

    fun getMyPost(): Flow<Result<List<Post>>>
}
