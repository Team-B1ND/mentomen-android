package kr.hs.dgsw.mentomenv2.data.mapper

import kr.hs.dgsw.mentomenv2.data.response.ImgUrlResponseDto
import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl

fun ImgUrlResponseDto.toModel(): ImgUrl =
    ImgUrl(
        imgUrl = this.imgUrl,
    )
