package kr.hs.dgsw.mentomenv2.data.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.data.request.CommentSubmitRequest
import kr.hs.dgsw.mentomenv2.data.request.CommentUpdateRequest
import kr.hs.dgsw.mentomenv2.domain.model.Comment

interface CommentDataSource {
    fun getCommentList(postId: Int): Flow<List<Comment>>

    fun submitComment(commentSubmitRequest: CommentSubmitRequest): Flow<Unit>

    fun updateComment(commentUpdateRequest: CommentUpdateRequest): Flow<Unit>

    fun deleteComment(commentId: Int): Flow<Unit>
}
