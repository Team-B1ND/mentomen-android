package kr.hs.dgsw.mentomenv2.data.datasource

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.domain.model.User

interface MyDataSource {
    fun getMyInfo(): Flow<User>

    fun getMyPost(): Flow<List<Post>>
}
