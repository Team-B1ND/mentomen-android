package kr.hs.dgsw.mentomenv2.data.mapper

import kr.hs.dgsw.mentomenv2.data.response.ImgUrlResponse
import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl

fun ImgUrlResponse.toModel(): ImgUrl =
    ImgUrl(
        imgUrl = this.imgUrl,
    )
