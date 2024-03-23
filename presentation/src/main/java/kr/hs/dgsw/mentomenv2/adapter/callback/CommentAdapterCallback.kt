package kr.hs.dgsw.mentomenv2.adapter.callback

interface CommentAdapterCallback {
    fun deleteComment(commentId: Int)

    fun updateIsEdit(
        isEdit: Boolean = false,
        commentId: Int = 0,
        value: String = "",
    )
}
