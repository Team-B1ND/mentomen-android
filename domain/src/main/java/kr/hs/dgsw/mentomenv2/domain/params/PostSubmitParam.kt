package kr.hs.dgsw.mentomenv2.domain.params

data class PostSubmitParam(
    val content : String,
    val imgUrls : List<String?> = emptyList(),
    val tag : String
)
