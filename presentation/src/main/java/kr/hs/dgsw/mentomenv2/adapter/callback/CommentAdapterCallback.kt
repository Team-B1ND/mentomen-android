package kr.hs.dgsw.mentomenv2.adapter.callback

interface CommentAdapterCallback {
    fun deleteComment(commentId: Int)
    fun updateComment(commentId: Int, content: String)
}