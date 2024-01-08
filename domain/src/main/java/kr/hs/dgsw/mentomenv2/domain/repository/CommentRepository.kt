package kr.hs.dgsw.mentomenv2.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.mentomenv2.domain.model.Comment
import kr.hs.dgsw.mentomenv2.domain.params.CommentSubmitParam
import kr.hs.dgsw.mentomenv2.domain.params.CommentUpdateParam
import kr.hs.dgsw.mentomenv2.domain.util.Result

interface CommentRepository {
    fun submitComment(commentSubmitParam: CommentSubmitParam): Flow<Result<Unit>>
    fun updateComment(commentUpdateParam: CommentUpdateParam): Flow<Result<Unit>>
    fun deleteComment(commentId: Int): Flow<Result<Unit>>
    fun getCommentList(postId: Int): Flow<Result<List<Comment>>>
}