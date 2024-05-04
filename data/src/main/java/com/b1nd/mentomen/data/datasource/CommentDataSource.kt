package com.b1nd.mentomen.data.datasource

import com.b1nd.mentomen.domain.model.Comment
import com.b1nd.mentomen.data.request.CommentSubmitRequest
import com.b1nd.mentomen.data.request.CommentUpdateRequest
import kotlinx.coroutines.flow.Flow

interface CommentDataSource {
    fun getCommentList(postId: Int): Flow<List<Comment>>

    fun submitComment(commentSubmitRequest: CommentSubmitRequest): Flow<Unit>

    fun updateComment(commentUpdateRequest: CommentUpdateRequest): Flow<Unit>

    fun deleteComment(commentId: Int): Flow<Unit>
}
