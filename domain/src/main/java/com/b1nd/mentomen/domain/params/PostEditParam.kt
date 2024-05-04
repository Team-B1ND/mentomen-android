package com.b1nd.mentomen.domain.params

import com.b1nd.mentomen.domain.model.ImgUrl

data class PostEditParam(
    val content: String,
    val imgUrls: List<ImgUrl?> = emptyList(),
    val tag: String,
    val postId: Int,
)
