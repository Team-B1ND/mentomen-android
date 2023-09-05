package kr.hs.dgsw.mentomenv2.data.mapper

import kr.hs.dgsw.mentomenv2.data.request.DAuthSignInRequest
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase

fun SignInUseCase.DAuthParam.toRequest() = DAuthSignInRequest(
    id = id,
    pw = pw,
    clientId = clientId,
    redirectUrl = redirectUrl,
    state = state,
)
