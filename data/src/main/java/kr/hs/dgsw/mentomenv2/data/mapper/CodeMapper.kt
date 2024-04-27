package kr.hs.dgsw.mentomenv2.data.mapper

import kr.hs.dgsw.mentomenv2.data.response.GetCodeResponse
import kr.hs.dgsw.mentomenv2.domain.model.Code

fun GetCodeResponse.toModel() = Code(
    code = this.location
)