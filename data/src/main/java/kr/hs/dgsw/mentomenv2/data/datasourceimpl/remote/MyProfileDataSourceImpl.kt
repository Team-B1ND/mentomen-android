package kr.hs.dgsw.mentomenv2.data.datasourceimpl.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.hs.dgsw.mentomenv2.data.datasource.MyProfileDataSource
import kr.hs.dgsw.mentomenv2.data.service.MyProfileService
import kr.hs.dgsw.mentomenv2.domain.exception.MenToMenException
import kr.hs.dgsw.mentomenv2.domain.model.User
import javax.inject.Inject

class MyProfileDataSourceImpl @Inject constructor(
    private val myProfileService: MyProfileService
) : MyProfileDataSource {
    override fun getMyInfo(): Flow<User> =
        flow {
            val response = myProfileService.getUserInfo().execute()
            if (response.isSuccessful) {
                emit(
                    User(
                        profileImage = response.body()?.data?.profileImage,
                        userId = response.body()?.data?.userId ?: 0,
                    ),
                )
            } else {
                throw MenToMenException(response.message())
            }
        }.flowOn(Dispatchers.IO)
}
