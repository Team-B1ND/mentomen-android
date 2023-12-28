package kr.hs.dgsw.mentomenv2.domain.model

data class Post(
    val author: Int,
    val content: String,
    val imgUrls: List<String?>? = emptyList(),
    val createDateTime: String,
    val postId: Int,
    val profileUrl: String,
    val stdInfo: StdInfo,
    val tag: String,
    val updateDateTime: String,
    val updateStatus: String,
    val userName: String,
    var isExpended: Boolean = false,
)
