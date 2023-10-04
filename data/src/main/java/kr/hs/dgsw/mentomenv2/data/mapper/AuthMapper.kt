package kr.hs.dgsw.mentomenv2.data.mapper

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

fun DAuthSignInResponse.toModel() = DAuthUser(
    name = name,
    profileImage = profileImage,
    location = location
)
