package com.b1nd.mentomen.domain.repository

import com.b1nd.mentomen.domain.model.Comment
import com.b1nd.mentomen.domain.params.CommentSubmitParam
import com.b1nd.mentomen.domain.params.CommentUpdateParam
import com.b1nd.mentomen.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun submitComment(commentSubmitParam: CommentSubmitParam): Flow<Result<Unit>>

    fun updateComment(commentUpdateParam: CommentUpdateParam): Flow<Result<Unit>>

    fun deleteComment(commentId: Int): Flow<Result<Unit>>

    fun getCommentList(postId: Int): Flow<Result<List<Comment>>>
}
