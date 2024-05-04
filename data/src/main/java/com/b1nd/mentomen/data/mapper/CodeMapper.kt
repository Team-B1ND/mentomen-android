package com.b1nd.mentomen.data.mapper

import com.b1nd.mentomen.domain.model.Code
import com.b1nd.mentomen.data.response.GetCodeResponse

fun GetCodeResponse.toModel() =
    Code(
        code = this.location,
    )
