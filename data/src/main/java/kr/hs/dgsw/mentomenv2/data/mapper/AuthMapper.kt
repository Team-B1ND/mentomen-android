package kr.hs.dgsw.mentomenv2.data.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.data.response.DAuthSignInResponse
import kr.hs.dgsw.mentomenv2.domain.model.DAuthUser
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase

fun SignInUseCase.DAuthParam.toRequest() = DAuthSignInRequest(
    id = id,
    pw = pw,
    clientId = clientId,
    redirectUrl = redirectUrl,
    state = state,
)

fun Flow<DAuthSignInResponse>.toModel(): Flow<DAuthUser> = flow {
    this@toModel.collect { dAuthUser ->
        val user = DAuthUser(
            name = dAuthUser.name,
            profileImage = dAuthUser.profileImage,
            location = dAuthUser.location
        )
    }
}
