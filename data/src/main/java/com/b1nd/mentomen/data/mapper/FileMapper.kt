package com.b1nd.mentomen.data.mapper

import com.b1nd.mentomen.data.response.ImgUrlResponse
import com.b1nd.mentomen.domain.model.ImgUrl

fun ImgUrlResponse.toModel(): ImgUrl =
    ImgUrl(
        imgUrl = this.imgUrl,
    )
