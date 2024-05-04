package com.b1nd.mentomen.adapter.callback

interface CommentAdapterCallback {
    fun deleteComment(commentId: Int)

    fun updateIsEdit(
        isEdit: Boolean = false,
        commentId: Int = 0,
        value: String = "",
    )
}
