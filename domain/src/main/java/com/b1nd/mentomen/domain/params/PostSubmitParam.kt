package com.b1nd.mentomen.domain.params

import com.b1nd.mentomen.domain.model.ImgUrl

data class PostSubmitParam(
    val content: String,
    val imgUrls: List<ImgUrl?> = emptyList(),
    val tag: String,
)
