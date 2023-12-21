package kr.hs.dgsw.mentomenv2.domain.params

import kr.hs.dgsw.mentomenv2.domain.model.ImgUrl

data class PostSubmitParam(
    val content : String,
    val imgUrls : List<ImgUrl?> = emptyList(),
    val tag : String
)
